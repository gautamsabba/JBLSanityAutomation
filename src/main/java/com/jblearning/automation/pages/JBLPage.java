package com.jblearning.automation.pages;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.ascendlearning.automation.ui.config.PropertiesRepository;
import com.ascendlearning.automation.ui.exceptions.DriverException;
import com.ascendlearning.automation.ui.handlers.LinkHandler;
import com.ascendlearning.automation.ui.handlers.MenuHandler;
import com.ascendlearning.automation.ui.handlers.TextHandler;
import com.ascendlearning.automation.ui.page.BasePage;

public class JBLPage extends BasePage {

	private Logger logger = LogManager.getLogger(this.getClass());

	public JBLPage(WebDriver webDriver) {
		super(webDriver);
	}

	public void loadMainPage() {
		driver.get(PropertiesRepository.getString("jblearning.mainpage.url"));
		setDriverWait(PropertiesRepository.getString("jblearning.mainpage.url.waitfor"));
	}

	public String selectBook() {
		MenuHandler menuHandler = new MenuHandler(driver);
		try {
			menuHandler.hoverOverMenuItem(
					PropertiesRepository.getString("jblearning.mainpage.menu.biophysoc"),
					PropertiesRepository.getString("jblearning.mainpage.menu.biophysoc.waitfor"));
		} catch (DriverException e) {
			logger.error("Error while hovering over menu item", e);
		}

		try {
			menuHandler.hoverOverMenuItem(
					PropertiesRepository.getString("jblearning.mainpage.menu.crijussoc"),
					PropertiesRepository.getString("jblearning.mainpage.menu.crijussoc.waitfor"));
		} catch (DriverException e) {
			logger.error("Error while hovering over menu item", e);
		}

		try {
			menuHandler.selectMenuItem(
					PropertiesRepository.getString("jblearning.mainpage.menu.corrections"),
					PropertiesRepository.getString("jblearning.mainpage.menu.corrections.waitfor"));
		} catch (DriverException e1) {
			logger.error("Error while selecting menu item", e1);
		}

		LinkHandler linkHandler = new LinkHandler(driver);
		try {
			linkHandler.selectLink(
					PropertiesRepository
							.getString("jblearning.mainpage.menu.corrections.selectbook"),
					PropertiesRepository.getString(
							"jblearning.bookdetailspage.menu.corrections.selectbook.waitfor"));
		} catch (DriverException e) {
			logger.error("Error while selecting link", e);
		}

		TextHandler textHandler = new TextHandler(driver);
		return textHandler.getText(
				PropertiesRepository.getString("jblearning.bookdetailspage.selectedbook.desc"));
	}

	/*
	 * TODO
	 * 
	 */
	public String purchaseBook() {
		String bookDesc = selectBook();

		LinkHandler linkHandler = new LinkHandler(driver);
		try {
			linkHandler.selectLink(
					PropertiesRepository
							.getString("jblearning.bookdetailspage.selectedbook.link.addcart"),
					PropertiesRepository.getString(
							"jblearning.bookdetailspage.menu.corrections.selectbook.waitfor"));
		} catch (DriverException e) {
			logger.error("Error while selecting link", e);
		}

		return null;
	}
}
