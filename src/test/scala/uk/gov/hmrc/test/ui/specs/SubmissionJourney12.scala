/*
 * Copyright 2024 HM Revenue & Customs
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

import org.scalatest.BeforeAndAfter
import uk.gov.hmrc.test.ui.pages.HomePage.signOutPage
import uk.gov.hmrc.test.ui.pages._
import util.CalculationDataUtil

import scala.collection.mutable.ArrayBuffer

class SubmissionJourney12 extends BaseSpec with BeforeAndAfter {
  var uniqueTaxSchemes: Map[String, String] = Map(
    ("Scheme 1" -> "00348916RU"),
    ("Scheme 2" -> "00348916RG")
  )
  var debitYears: ArrayBuffer[Int]          = ArrayBuffer(2020)

  Feature("PRA Submission Journey 2, Business scenario AA journeys") {

    /** User has debit, public scheme to pay, date of election known, no alternate name, UK resident, not claiming higher rate relief
      */
    /** 5.2(N debit), 5.10(Scheme paid), 5.11(public), ..... */

    Scenario("Calculate PRA Submission Journey 2") {
      val calculationData = new CalculationDataUtil()
      calculationData.submitCalculation("calculationDataSet11")

      When("User landed to SubmissionInfo page ")
      SubmissionInfoPage.verifySubmissionInfoPageAndContinue()

      When("I verify ClaimOnBehalfPage, select yes and click continue button")
      ClaimOnBehalfPage.verifyPageSelectNoAndContinue()

      debitYears.foreach { element =>
        When("I verify WhoWillPayPage, select pension scheme and click continue button")
        WhoWillPayPage.verifyPageSelectPensionSchemeAndContinue()

        // dataset doesnt include NHS
        When("I verify WhichPensionSchemeWillPayPage, select public pension scheme and click continue button")
        WhichPensionSchemeWillPayPage.verifyPageSelectPSAndContinue("Scheme 1")

        When("I verify Valid Election for Scheme to pay Page and select yes and click continue")
        AskedPensionSchemeToPayTaxCharge.verifyPageSelectYesAndContinue()

        When("I verify Estimated quarter of Election Page and select quarter and click continue")
        WhenDidYouAskPensionSchemeToPay.verifyPageEnterDateAndContinue()
      }

      When("I verify AlternativeNamePage, select yes and continue")
      AlternativeNamePage.verifyPageSelectYesAndContinue()

      When("I verify ContactNumberPage, enter phone number and continue")
      ContactNumberPage.verifyPageEnterContactNumberAndContinue("07884554867")

      When("I verify ResidencePage select NO and Continue")
      ResidencePage.verifyPageSelectNoAndContinue()

      When("I verify UKAddressPage enter address and Continue")
      InternationalAddressPage.verifyPageEnterAddressAndContinue()

      uniqueTaxSchemes.foreach { case (key, value) =>
        When("I verify LegacyPensionSchemeReferencePage enter reference and Continue")
        LegacyPensionSchemeReferencePage.verifyPageEnterReferenceAndContinue(key, value)

        When("I verify ReformPensionSchemeReferencePage enter reference and Continue")
        ReformPensionSchemeReferencePage.verifyPageEnterReferenceAndContinue(value)
      }

      When("I verify ClaimingAdditionalTaxRateRelief Page , select no and click continue")
      ClaimingAdditionalTaxRateReliefPage.verifyPageClickNoAndContinue()

      When("I verify Bank Details Page, enter bank details and click continue")
      BankDetailsPage.verifyPageEnterBankDetailsClickContinue()

      CheckYourAnswersCalculationJourneyPage.clickSubmissionContinueButton()

      When("I verify DeclarationsPage Page and click confirm")
      DeclarationsPage.verifyPageAndConfirm()

      Then("I verify SubmissionPage Page")
      SubmissionPage.verifySubmissionPage()

      signOutPage()

    }
  }
}
