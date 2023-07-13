/*
 * Copyright 2023 HM Revenue & Customs
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
 */

package uk.gov.hmrc.test.ui.specs

import uk.gov.hmrc.test.ui.pages.{AuthorityWizardPage, ClaimOnBehalfPage, HomePage, StatusOfUserPage, SubmissionInfoPage}
import uk.gov.hmrc.test.ui.specs.tags.ZapTests

class SubmissionUserJourney extends BaseSpec {

  Feature("Submission user journey tests") {

    /** Below journey covers 5.1, 5.2, 5.3 pages in the mural board* */
    Scenario("5.2 Claiming on behalf of yes user journey", ZapTests) {
      Given("I'm an authorized User and navigated to Public Service Pensions Remediation home page")
      AuthorityWizardPage.authorizedLoginUser()
      HomePage.loadHomePage()

      When("I click start button")
      HomePage.clickStartButton()

      When("I verify SubmissionInfoPage and click continue button")
      SubmissionInfoPage.verifySubmissionInfoPageAndContinue()

      When("I verify ClaimOnBehalfPage, select yes and click continue button")
      ClaimOnBehalfPage.verifyPageSelectYesAndContinue()

      Then("I verify landed to StatusOfUserPage")
      StatusOfUserPage.verifyStatusOfUserPage()
    }
  }

}
