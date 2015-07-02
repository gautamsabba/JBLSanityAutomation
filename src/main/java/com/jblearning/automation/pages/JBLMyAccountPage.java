package com.jblearning.automation.pages;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.ascendlearning.automation.ui.config.PropertiesRepository;
import com.ascendlearning.automation.ui.exceptions.DriverException;
import com.ascendlearning.automation.ui.handlers.LinkHandler;
import com.ascendlearning.automation.ui.handlers.TextHandler;
import com.ascendlearning.automation.ui.handlers.WindowHandler;
import com.ascendlearning.automation.ui.page.BasePage;

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
			linkHandler.setSizzleSelector();
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
			linkHandler.setCSSSelector();
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

}
