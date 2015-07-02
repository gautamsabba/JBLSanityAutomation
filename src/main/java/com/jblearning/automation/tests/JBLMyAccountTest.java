package com.jblearning.automation.tests;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.ascendlearning.automation.ui.test.BaseTest;
import com.jblearning.automation.pages.JBLMyAccountPage;

public class JBLMyAccountTest extends BaseTest {
	Logger logger = LogManager.getLogger(this.getClass());

	@Test(groups = { "smoke" })
	public void testLoginToMyAccount() {
		JBLMyAccountPage myAccPage = new JBLMyAccountPage(driver);
		myAccPage.login();
	}
}
