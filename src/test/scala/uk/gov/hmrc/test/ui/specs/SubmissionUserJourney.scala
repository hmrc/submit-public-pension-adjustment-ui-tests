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

    /** Below journey covers 5.1, 5.2, 5.3, 5.4, 5.5, 5.6, 5.7, 5.8(Y), 5.9, 5.15(Y), 5.17, 5.18(Y), 5.19, 5.20, 5.21, 5.22, 5.26, 5.27, 5.28 pages in the mural board* */
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

      When("I verify TheirNinoPage enter NINO and continue")
      TheirNinoPage.verifyPageEnterNinoAndContinue()

      When("I verify TheirUTRPage enter tax reference and continue")
      TheirUTRPage.verifyPageEnterUTRAndContinue()

      When("I verify TheirResidencePage, select yes and continue")
      TheirResidencePage.verifyPageSelectYesAnContinue()

      When("I verify TheirUKAddressPage, Enter Address information and continue")
      TheirUKAddressPage.verifyPageEnterAddressAndContinue()

      When("I verify AlternativeNamePage, select Yes and continue")
      AlternativeNamePage.verifyPageSelectYesAndContinue()

      When("I verify ContactNumberPage and continue without phone number")
      ContactNumberPage.verifyPageAndContinueWithoutContactNumber()

      When("I verify ResidencePage select Yes and Continue")
      ResidencePage.verifyPageSelectYesAndContinue()

      When("I verify UKAddressPage enter address and Continue")
      UKAddressPage.verifyPageEnterAddressAndContinue()

      When("I verify LegacyPensionSchemeReferencePage enter reference and Continue")
      LegacyPensionSchemeReferencePage.verifyPageEnterReferenceAndContinue()

      When("I verify ReformPensionSchemeReferencePage enter reference and Continue")
      ReformPensionSchemeReferencePage.verifyPageEnterReferenceAndContinue()

      /** Verify No member credit work flows */
      When("I verify ClaimingAdditionalTaxRateRelief Page , select no and click continue")
      ClaimingAdditionalTaxRateReliefPage.verifyPageClickNoAndContinue()

      When("I verify DeclarationsPage Page and click confirm")
      DeclarationsPage.verifyPageAndConfirm()

      /** Verify check your answers page */
      When("I verify CheckYourAnswersPage Page and click continue")
      CheckYourAnswersPage.verifyCheckYourAnswersPageAndContinue()

      Then("I verify SubmissionPage")
      SubmissionPage.verifySubmissionPage()
    }

    /** Below journey covers 5.1, 5.2, 5.3, 5.4, 5.5, 5.6, 5.7, 5.8(N), 5.15, 5.16, 5.17, 5.18(N), 5.20, 5.21, 5.22, 5.23, 5.24,5.26,5.27, 5.28 pages in the mural board* */
    Scenario("5.7 continue without tax reference user journey", ZapTests) {
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

      When("I verify TheirNinoPage enter NINO and continue")
      TheirNinoPage.verifyPageEnterNinoAndContinue()

      When("I verify TheirUTRPage and continue without tax reference")
      TheirUTRPage.verifyPageContinueWithoutTaxReference()

      When("I verify TheirResidencePage, select no and continue")
      TheirResidencePage.verifyPageSelectNoAnContinue()

      When("I verify TheirInternationalAddressPage, Enter Address information and continue")
      TheirInternationalAddressPage.verifyPageEnterAddressAndContinue()

      When("I verify AlternativeNamePage, select No and continue")
      AlternativeNamePage.verifyPageSelectNoAndContinue()

      When("I verify AlternativeNamePage, enter name and continue")
      EnterAlternativeNamePage.verifyPageEnterNameAndContinue()

      When("I verify ContactNumberPage, enter phone number and continue")
      ContactNumberPage.verifyPageEnterContactNumberAndContinue()

      When("I verify ResidencePage select Yes and Continue")
      ResidencePage.verifyPageSelectNoAndContinue()

      When("I verify InternationalAddressPage enter address and Continue")
      InternationalAddressPage.verifyPageEnterAddressAndContinue()

      When("I verify LegacyPensionSchemeReferencePage enter reference and Continue")
      LegacyPensionSchemeReferencePage.verifyPageEnterReferenceAndContinue()

      When("I verify ReformPensionSchemeReferencePage enter reference and Continue")
      ReformPensionSchemeReferencePage.verifyPageEnterReferenceAndContinue()

      When("I verify ClaimingAdditionalTaxRateRelief Page , select yes and click continue")
      ClaimingAdditionalTaxRateReliefPage.verifyPageClickYesAndContinue()

      When("I verify TaxReliefAmountPage Page, enter tax relief and click continue")
      TaxReliefAmountPage.verifyPageEnterTaxReliefAndContinue()

      When("I verify WhichPensionSchemeWillPayTaxReliefPage Page, select pension scheme and click continue")
      WhichPensionSchemeWillPayTaxReliefPage.verifyPageSelectPensionSchemeAndContinue("Pension Scheme A")

      When("I verify Bank Details Page, enter bank details and click continue")
      BankDetailsPage.verifyPageEnterBankDetailsClickContinue()

      When("I verify DeclarationsPage Page and click confirm")
      DeclarationsPage.verifyPageAndConfirm()

      /** Verify the content in check your answers page */
      When("I verify CheckYourAnswersPage Page and click continue")
      CheckYourAnswersPage.verifyCheckYourAnswersPageAndContinue()

      Then("I verify SubmissionPage")
      SubmissionPage.verifySubmissionPage()

    }

    /** Below journey covers 5.1, 5.2, 5.10, 5.11, 5.11.1, 5.12, 5.14, 5.15, 5.16, 5.17, 5.18(N), 5.20, 5.21, 5.22, 5.23, 5.24,5.25, 5.26,5.27, 5.28  pages in the mural board* */
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
      WhenDidYouAskPensionSchemeToPay.verifyPageEnterDateAndContinue()

      When("I verify AlternativeNamePage, select No and continue")
      AlternativeNamePage.verifyPageSelectNoAndContinue()

      When("I verify AlternativeNamePage, enter name and continue")
      EnterAlternativeNamePage.verifyPageEnterNameAndContinue()

      When("I verify ContactNumberPage, enter phone number and continue")
      ContactNumberPage.verifyPageEnterContactNumberAndContinue()

      When("I verify ResidencePage select Yes and Continue")
      ResidencePage.verifyPageSelectNoAndContinue()

      When("I verify InternationalAddressPage enter address and Continue")
      InternationalAddressPage.verifyPageEnterAddressAndContinue()

      When("I verify LegacyPensionSchemeReferencePage enter reference and Continue")
      LegacyPensionSchemeReferencePage.verifyPageEnterReferenceAndContinue()

      When("I verify ReformPensionSchemeReferencePage enter reference and Continue")
      ReformPensionSchemeReferencePage.verifyPageEnterReferenceAndContinue()

      When("I verify ClaimingAdditionalTaxRateRelief Page , select yes and click continue")
      ClaimingAdditionalTaxRateReliefPage.verifyPageClickYesAndContinue()

      When("I verify TaxReliefAmountPage Page, enter tax relief and click continue")
      TaxReliefAmountPage.verifyPageEnterTaxReliefAndContinue()

      When("I verify WhichPensionSchemeWillPayTaxReliefPage Page, select pension scheme and click continue")
      WhichPensionSchemeWillPayTaxReliefPage.verifyPageSelectPensionSchemeAndContinue("Pension Scheme A")

      When("I verify Bank Details Page, enter bank details and click continue")
      BankDetailsPage.verifyPageEnterBankDetailsClickContinue()

      When("I verify DeclarationsPage Page and click confirm")
      DeclarationsPage.verifyPageAndConfirm()

      /** Verify the content in check your answers page */
      When("I verify CheckYourAnswersPage Page and click continue")
      CheckYourAnswersPage.verifyCheckYourAnswersPageAndContinue()

      Then("I verify SubmissionPage")
      SubmissionPage.verifySubmissionPage()

    }

    /** Below journey covers 5.1, 5.2, 5.10, 5.11, 5.12, 5.13, 5.15(Y), 5.17, 5.18(Y), 5.19, 5.20, 5.21, 5.22, 5.26, 5.27, 5.28pages in the mural board* */
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
      WhenWillYouAskPensionSchemeToPay.verifyPageSelectQuarterAndContinue()

      When("I verify AlternativeNamePage, select Yes and continue")
      AlternativeNamePage.verifyPageSelectYesAndContinue()

      When("I verify ContactNumberPage and continue without phone number")
      ContactNumberPage.verifyPageAndContinueWithoutContactNumber()

      When("I verify ResidencePage select Yes and Continue")
      ResidencePage.verifyPageSelectYesAndContinue()

      When("I verify UKAddressPage enter address and Continue")
      UKAddressPage.verifyPageEnterAddressAndContinue()

      When("I verify LegacyPensionSchemeReferencePage enter reference and Continue")
      LegacyPensionSchemeReferencePage.verifyPageEnterReferenceAndContinue()

      When("I verify ReformPensionSchemeReferencePage enter reference and Continue")
      ReformPensionSchemeReferencePage.verifyPageEnterReferenceAndContinue()

      /** Verify No member credit work flows */
      When("I verify ClaimingAdditionalTaxRateRelief Page , select no and click continue")
      ClaimingAdditionalTaxRateReliefPage.verifyPageClickNoAndContinue()

      When("I verify DeclarationsPage Page and click confirm")
      DeclarationsPage.verifyPageAndConfirm()

      /** Verify check your answers page */
      When("I verify CheckYourAnswersPage Page and click continue")
      CheckYourAnswersPage.verifyCheckYourAnswersPageAndContinue()

      Then("I verify SubmissionPage")
      SubmissionPage.verifySubmissionPage()
    }

    /** Below journey covers 5.1, 5.2, 5.10, 5.15(Y), 5.17, 5.18(Y), 5.19, 5.20, 5.21, 5.22, 5.26, 5.27, 5.28 pages in the mural board* */
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

      When("I verify AlternativeNamePage, select Yes and continue")
      AlternativeNamePage.verifyPageSelectYesAndContinue()

      When("I verify ContactNumberPage and continue without phone number")
      ContactNumberPage.verifyPageAndContinueWithoutContactNumber()

      When("I verify ResidencePage select Yes and Continue")
      ResidencePage.verifyPageSelectYesAndContinue()

      When("I verify UKAddressPage enter address and Continue")
      UKAddressPage.verifyPageEnterAddressAndContinue()

      When("I verify LegacyPensionSchemeReferencePage enter reference and Continue")
      LegacyPensionSchemeReferencePage.verifyPageEnterReferenceAndContinue()

      When("I verify ReformPensionSchemeReferencePage enter reference and Continue")
      ReformPensionSchemeReferencePage.verifyPageEnterReferenceAndContinue()

      /** Verify No member credit work flows */
      When("I verify ClaimingAdditionalTaxRateRelief Page , select no and click continue")
      ClaimingAdditionalTaxRateReliefPage.verifyPageClickNoAndContinue()

      When("I verify DeclarationsPage Page and click confirm")
      DeclarationsPage.verifyPageAndConfirm()

      /** Verify check your answers page */
      When("I verify CheckYourAnswersPage Page and click continue")
      CheckYourAnswersPage.verifyCheckYourAnswersPageAndContinue()

      Then("I verify SubmissionPage")
      SubmissionPage.verifySubmissionPage()

    }

  }

}
