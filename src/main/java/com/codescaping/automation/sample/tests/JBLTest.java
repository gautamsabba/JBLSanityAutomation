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
package com.codescaping.automation.sample.tests;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.codescaping.automation.ui.assertions.VerificationHandler;
import com.codescaping.automation.ui.config.PropertiesRepository;
import com.codescaping.automation.ui.test.BaseTest;
import com.codescaping.automation.sample.pages.JBLPage;

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
