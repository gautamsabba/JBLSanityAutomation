/*******************************************************************************
 * Copyright (c) 2015 Gautam Sabba.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.codescaping.automation.sample.pages;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.codescaping.automation.ui.config.PropertiesRepository;
import com.codescaping.automation.ui.exceptions.DriverException;
import com.codescaping.automation.ui.handlers.LinkHandler;
import com.codescaping.automation.ui.handlers.TextHandler;
import com.codescaping.automation.ui.handlers.WindowHandler;
import com.codescaping.automation.ui.page.BasePage;

public class JBLMyAccountPage extends BasePage {

	private Logger logger = LogManager.getLogger(this.getClass());

	public JBLMyAccountPage(WebDriver webDriver) {
		super(webDriver);
	}

	public void login() {
		// Load the first page
		loadMainPage();

		// Go to My Account page
		LinkHandler linkHandler = new LinkHandler(driver);
		try {
			linkHandler.selectLink(
					PropertiesRepository.getString("jblmyaccount.mainpage.link.myaccount"),
					PropertiesRepository.getString("jblmyaccount.mainpage.link.myaccount.waitfor"));
		} catch (DriverException de) {
			logger.error("Unable to open My Account page", de);
		}

		// Dismiss the Security Check dialog
		WindowHandler windowHandler = new WindowHandler(driver);
		WebElement dialog = windowHandler.switchToModalDialog();
		dialog.click();

		// Enter login details
		TextHandler textHandler = new TextHandler(driver);
		try {
			textHandler.writeText(
					PropertiesRepository.getString("jblearning.loginpage.textfield.username"),
					PropertiesRepository
							.getString("jblearning.loginpage.textfield.value.username"));

			textHandler.writeText(
					PropertiesRepository.getString("jblearning.loginpage.textfield.password"),
					PropertiesRepository
							.getString("jblearning.loginpage.textfield.value.password"));
		} catch (DriverException e) {
			logger.error("Unable to enter username and/or password", e);			
		}
		
		//Login		
		try {
			linkHandler.selectLink(
					PropertiesRepository.getString("jblearning.loginpage.link.login"),
					PropertiesRepository.getString("jblearning.loginpage.link.login.waitfor"));
		} catch (DriverException e) {
			logger.error("Unable to login", e);
		}
	}

	private void loadMainPage() {
		driver.get(PropertiesRepository.getString("jblmyaccount.mainpage.url"));
		setDriverWait(PropertiesRepository.getString("jblmyaccount.mainpage.url.waitfor"));
	}

	/**
	 * Insure that various SSO pages (Nav1, Nav2, etc.) are working fine
	 */
	public void checkSSO() {
		// Login to MyAccount
		login();

		// Check Nav 1 Course
		LinkHandler linkHandler = new LinkHandler(driver);
		try {
			linkHandler.selectLink(
					PropertiesRepository.getString("jblearning.productpage.link.nav1course"),
					PropertiesRepository
							.getString("jblearning.productpage.link.nav1course.waitfor"));
		} catch (DriverException e) {
			logger.error("Unable to find Nav 1 product", e);
		}

		// Launch the course
		try {
			WindowHandler windowHandler = linkHandler.selectLinkToLaunchNewWindow(
					PropertiesRepository.getString("jblearning.productdetailspage.link.nav1course"),
					PropertiesRepository
							.getString("jblearning.productdetailspage.link.nav1course.waitfor"));
			WebDriver mainWindow = windowHandler.switchToMainWindow();
			windowHandler.cleanUp(mainWindow);
		} catch (DriverException e) {
			logger.error("Unable to launch Nav 1 product", e);
		}
	}
}
