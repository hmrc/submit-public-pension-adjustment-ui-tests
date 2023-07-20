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

import uk.gov.hmrc.test.ui.pages._
import uk.gov.hmrc.test.ui.specs.tags.ZapTests

class SubmissionUserJourney extends BaseSpec {

  Feature("Submission user journey tests") {

    /** Below journey covers 5.1, 5.2, 5.3, 5.4, 5.5 pages in the mural board* */
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

      When("I verify StatusOfUserPage select deputyship and continue")
      StatusOfUserPage.verifyPageSelectDeputyshipAndContinue()

      When("I verify TheirNamePage enter pension scheme name and continue")
      TheirNamePage.verifyPageEnterPensionSchemeNameAndContinue()

      When("I verify TheirDOBPage enter date of birth and continue")
      TheirDOBPage.verifyPageEnterBirthdayAndContinue()
    }

    /** Below journey covers 5.1, 5.2, 5.10, 5.11, 5.11.1, 5.12, 5.14  pages in the mural board* */
    Scenario("5.11 select scheme details - private ps, user journey", ZapTests) {
      Given("I'm an authorized User and navigated to Public Service Pensions Remediation home page")
      AuthorityWizardPage.authorizedLoginUser()
      HomePage.loadHomePage()

      When("I click start button")
      HomePage.clickStartButton()

      When("I verify SubmissionInfoPage and click continue button")
      SubmissionInfoPage.verifySubmissionInfoPageAndContinue()

      When("I verify ClaimOnBehalfPage, select no and click continue button")
      ClaimOnBehalfPage.verifyPageSelectNoAndContinue()

      When("I verify WhoWillPayPage, select pension scheme and click continue button")
      WhoWillPayPage.verifyPageSelectPensionSchemeAndContinue()

      When("I verify WhichPensionSchemeWillPayPage, select private pension scheme and click continue button")
      WhichPensionSchemeWillPayPage.verifyPageSelectPrivatePSAndContinue()

      When("I verify PensionSchemeDetailsPage, enter pension scheme information and click continue button")
      PensionSchemeDetailsPage.verifyPageEnterPensionSchemeInformationAndContinue()

      Then("I verify Valid Election for Scheme to pay Page and select yes and click continue")
      AskedPensionSchemeToPayTaxCharge.verifyPageSelectYesAndContinue()

      Then("I verify Date of Election Page and enter date and click continue")
      WhenDidYouAskPensionSchemeToPay.verifyWhenDidYouAskPensionSchemeToPayPageEnterDateAndContinue()

    }

    /** Below journey covers 5.1, 5.2, 5.10, 5.11, 5.12, 5.13  pages in the mural board* */
    Scenario(
      "5.11 select scheme details - public ps, user journey, not made valid election for the scheme to pay",
      ZapTests
    ) {
      Given("I'm an authorized User and navigated to Public Service Pensions Remediation home page")
      AuthorityWizardPage.authorizedLoginUser()
      HomePage.loadHomePage()

      When("I click start button")
      HomePage.clickStartButton()

      When("I verify SubmissionInfoPage and click continue button")
      SubmissionInfoPage.verifySubmissionInfoPageAndContinue()

      When("I verify ClaimOnBehalfPage, select no and click continue button")
      ClaimOnBehalfPage.verifyPageSelectNoAndContinue()

      When("I verify WhoWillPayPage, select pension scheme and click continue button")
      WhoWillPayPage.verifyPageSelectPensionSchemeAndContinue()

      When("I verify WhichPensionSchemeWillPayPage, select public pension scheme and click continue button")
      WhichPensionSchemeWillPayPage.verifyPageSelectPSAndContinue("pensionSchemeA")

      When("I verify Valid Election for Scheme to pay Page and select yes and click continue")
      AskedPensionSchemeToPayTaxCharge.verifyPageSelectNoAndContinue()

      When("I verify Estimated quarter of Election Page and select quarter and click continue")
      WhenWillYouAskPensionSchemeToPay.verifyWhenWillYouAskPensionSchemeToPayPageSelectQuarterAndContinue()

      /** Mock the url to support 5.22 navigation */
      When("I verify ClaimingAdditionalTaxRateReliefPage Page , select yes and click continue")
      ClaimingAdditionalTaxRateReliefPage.verifyPageClickYesAndContinue()

      When("I verify TaxReliefAmountPage Page, enter tax relief and click continue")
      TaxReliefAmountPage.verifyPageEnterTaxReliefAndContinue()

      /** Need to implement navigation after this step */
      When("I verify WhichPensionSchemeWillPayTaxReliefPage Page, select pension scheme and click continue")
      WhichPensionSchemeWillPayTaxReliefPage.verifyPageSelectPensionSchemeAndContinue("Pension Scheme A")

      /** Mock the url to support 5.26 navigation */
      When("I verify DeclarationsPage Page and click confirm")
      DeclarationsPage.verifyPageAndConfirm()

      /** Verify the content in check your answers page */
      When("I verify CheckYourAnswersPage Page and click continue")
      CheckYourAnswersPage.verifyCheckYourAnswersPageAndContinue()

      Then("I verify SubmissionPage")
      SubmissionPage.verifySubmissionPage()

    }

    /** Below journey covers 5.1, 5.2, 5.10, 5.15 pages in the mural board* */
    Scenario("5.10 who's is going to pay new charge you, user journey", ZapTests) {
      Given("I'm an authorized User and navigated to Public Service Pensions Remediation home page")
      AuthorityWizardPage.authorizedLoginUser()
      HomePage.loadHomePage()

      When("I click start button")
      HomePage.clickStartButton()

      When("I verify SubmissionInfoPage and click continue button")
      SubmissionInfoPage.verifySubmissionInfoPageAndContinue()

      When("I verify ClaimOnBehalfPage, select no and click continue button")
      ClaimOnBehalfPage.verifyPageSelectNoAndContinue()

      When("I verify WhoWillPayPage, select you and click continue button")
      WhoWillPayPage.verifyPageSelectYouAndContinue()

      Then("I verify AlternativeNamePage, and verify page")
      AlternativeNamePage.verifyAlternativeNamePage()

    }

  }

}
