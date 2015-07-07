package com.jblearning.automation.pages;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.ascendlearning.automation.ui.config.PropertiesRepository;
import com.ascendlearning.automation.ui.exceptions.DriverException;
import com.ascendlearning.automation.ui.handlers.LinkHandler;
import com.ascendlearning.automation.ui.handlers.MenuHandler;
import com.ascendlearning.automation.ui.handlers.RadioButtonHandler;
import com.ascendlearning.automation.ui.handlers.TextHandler;
import com.ascendlearning.automation.ui.page.BasePage;

public class JBLPage extends BasePage {

	private Logger logger = LogManager.getLogger(this.getClass());

	public JBLPage(WebDriver webDriver) {
		super(webDriver);
	}

	private void loadMainPage() {
		driver.get(PropertiesRepository.getString("jblearning.mainpage.url"));
		setDriverWait(PropertiesRepository.getString("jblearning.mainpage.url.waitfor"));
	}

	public String selectBook() {
		// Load main page
		loadMainPage();

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
		String bookDesc = null;
		try {
			bookDesc = textHandler.getText(
					PropertiesRepository.getString("jblearning.bookdetailspage.selectedbook.desc"));
		} catch (DriverException e) {
			logger.error("Unable to get book description", e);
		}
		return bookDesc;
	}

	public String purchaseBook() {
		// Load main page
		loadMainPage();

		// Select a book
		selectBook();

		try {
			LinkHandler linkHandler = new LinkHandler(driver);
			TextHandler textHandler = new TextHandler(driver);
			RadioButtonHandler radioButtonHandler = new RadioButtonHandler(driver);

			// Add book to cart
			linkHandler.selectLink(
					PropertiesRepository.getString("jblearning.bookdetailspage.link.add2cart"),
					PropertiesRepository
							.getString("jblearning.bookdetailspage.link.add2cart.waitfor"));

			// Checkout book
			linkHandler.selectLink(
					PropertiesRepository.getString("jblearning.checkoutpage.link.checkout"),
					PropertiesRepository
							.getString("jblearning.checkoutpage.link.checkout.waitfor"));

			// Login before checking out

			// Enter user name
			textHandler.writeText(
					PropertiesRepository
							.getString("jblearning.checkoutoptionspage.textfield.username"),
					PropertiesRepository
							.getString("jblearning.checkoutoptionspage.textfield.value.username"));

			// Enter password
			textHandler.writeText(
					PropertiesRepository
							.getString("jblearning.checkoutoptionspage.textfield.password"),
					PropertiesRepository
							.getString("jblearning.checkoutoptionspage.textfield.value.password"));

			// Login
			linkHandler.selectLink(
					PropertiesRepository.getString("jblearning.checkoutoptionspage.link.login"),
					PropertiesRepository
							.getString("jblearning.checkoutoptionspage.link.login.waitfor"));

			// Fill out shipping information

			// Enter email id
			textHandler.writeText(
					PropertiesRepository
							.getString("jblearning.checkoutcustinfopage.textfield.emailaddress"),
					PropertiesRepository.getString(
							"jblearning.checkoutcustinfopage.textfield.value.emailaddress"));

			// Enter shipping instructions
			textHandler.writeText(
					PropertiesRepository.getString(
							"jblearning.checkoutcustinfopage.textarea.shippinginstructions"),
					PropertiesRepository.getString(
							"jblearning.checkoutcustinfopage.textarea.value.shippinginstructions"));

			// Choose Purchase Order instead of Credit Card
			radioButtonHandler.selectRadioButton(
					PropertiesRepository
							.getString("jblearning.checkoutcustinfopage.radio.purchaseorder"),
					PropertiesRepository.getString(
							"jblearning.checkoutcustinfopage.radio.purchaseorder.waitfor"));

			// Enter PO Details
			textHandler.writeText(
					PropertiesRepository
							.getString("jblearning.checkoutcustinfopage.textfield.ponumber"),
					PropertiesRepository
							.getString("jblearning.checkoutcustinfopage.textfield.value.ponumber"));
			textHandler.writeText(
					PropertiesRepository
							.getString("jblearning.checkoutcustinfopage.textfield.pocontactname"),
					PropertiesRepository.getString(
							"jblearning.checkoutcustinfopage.textfield.value.pocontactname"));
			textHandler.writeText(
					PropertiesRepository
							.getString("jblearning.checkoutcustinfopage.textfield.pocontactnumber"),
					PropertiesRepository.getString(
							"jblearning.checkoutcustinfopage.textfield.value.pocontactnumber"));

			// Continue to complete checkout page
			linkHandler.selectLink(
					PropertiesRepository.getString("jblearning.checkoutcustinfopage.link.continue"),
					PropertiesRepository
							.getString("jblearning.checkoutcustinfopage.link.continue.waitfor"));

			// Complete checkout
			linkHandler.selectLink(
					PropertiesRepository
							.getString("jblearning.ordersummarypage.link.completecheckout"),
					PropertiesRepository.getString(
							"jblearning.ordersummarypage.link.completecheckout.waitfor"));

		} catch (DriverException e) {
			logger.error("Error while purchasing book", e);
		}
		return PropertiesRepository
				.getString("jblearning.ordersummarypage.link.completecheckout.waitfor");
	}
}
