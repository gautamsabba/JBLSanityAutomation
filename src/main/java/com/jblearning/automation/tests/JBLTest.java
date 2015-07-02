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
		String bookDesc = jblPage.selectBook();
		VerificationHandler.verifyEquals(bookDesc,
				PropertiesRepository.getString("jblearning.bookdetailspage.selectedbook.desc.cd"));
	}

	@Test(groups = { "smoke" })
	public void testLoadJBLearningMainPageAndPurchaseBook() {
		JBLPage jblPage = new JBLPage(driver);
		String result = jblPage.purchaseBook();
		VerificationHandler.verifyEquals(result, PropertiesRepository
				.getString("jblearning.ordersummarypage.link.completecheckout.desc.cd"));
	}
}
