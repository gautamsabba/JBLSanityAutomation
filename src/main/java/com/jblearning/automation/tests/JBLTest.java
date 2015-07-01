package com.jblearning.automation.tests;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.ascendlearning.automation.ui.assertions.VerificationHandler;
import com.ascendlearning.automation.ui.config.PropertiesRepository;
import com.ascendlearning.automation.ui.test.BaseTest;
import com.jblearning.automation.pages.JBLPage;

public class JBLTest extends BaseTest {
	Logger logger = LogManager.getLogger(this.getClass());

	@Test(groups = { "smoke" })
	public void testLoadJBLearningMainPageAndSelectBook() {
		JBLPage jblPage = new JBLPage(driver);
		jblPage.loadMainPage();
		String bookDesc = jblPage.selectBook();
		VerificationHandler.verifyEquals(bookDesc, PropertiesRepository
				.getString("jblearning.mainpage.menu.corrections.selectbook.desc.cd"));
	}

	public void testLoadJBLearningMainPageAndPurchaseBook() {
		JBLPage jblPage = new JBLPage(driver);
		jblPage.loadMainPage();
		String bookDesc = jblPage.selectBook();
		VerificationHandler.verifyEquals(bookDesc, PropertiesRepository
				.getString("jblearning.mainpage.menu.corrections.selectbook.desc.cd"));
	}
}
