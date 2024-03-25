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
import uk.gov.hmrc.test.ui.specs.tags.ZapTests
import util.CalculationDataUtil

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

class SubmissionJourney10 extends BaseSpec with BeforeAndAfter {
  var uniqueTaxSchemes: Map[String, String] = Map(
    /** add scheme details from the test json to below map * */
    ("Scheme 1" -> "00348916RU"),
    ("Scheme 4" -> "00348916RC")
  )
  var debitYears: ArrayBuffer[Int]          = ArrayBuffer(
    2020,
    2022,
    2023
  )

  Feature("Business scenario AA journeys") {

    /** 5.2(N debit), 5.10(Scheme paid), 5.11(public), 5.12(N), 5.13, 5.15, 5.16, 5.17, 5.18(N), 5.20, 5.21, 5.22, 5.23, 5.26 */
    Scenario(s"Calculate Business Journey10", ZapTests) {
      val calculationData = new CalculationDataUtil()
      calculationData.submitCalculation("calculationDataSet10")

      When("User landed to SubmissionInfo page ")
      SubmissionInfoPage.verifySubmissionInfoPageAndContinue()

      When("I verify ClaimOnBehalfPage, select yes and click continue button")
      ClaimOnBehalfPage.verifyPageSelectNoAndContinue()

      debitYears.foreach { element =>
        When("I verify WhoWillPayPage, select pension scheme and click continue button")
        WhoWillPayPage.verifyPageSelectPensionSchemeAndContinue()

        When("I verify WhichPensionSchemeWillPayPage, select public pension scheme and click continue button")
        WhichPensionSchemeWillPayPage.verifyPageSelectPSAndContinue("Scheme 1")

        When("I verify Valid Election for Scheme to pay Page and select yes and click continue")
        AskedPensionSchemeToPayTaxCharge.verifyPageSelectNoAndContinue()

        When("I verify Estimated quarter of Election Page and select quarter and click continue")
        WhenWillYouAskPensionSchemeToPay.verifyPageSelectQuarterAndContinue()
      }

      When("I verify AlternativeNamePage, select No and continue")
      AlternativeNamePage.verifyPageSelectNoAndContinue()

      When("I verify AlternativeNamePage, enter name and continue")
      EnterAlternativeNamePage.verifyPageEnterNameAndContinue("john")

      When("I verify ContactNumberPage, enter phone number and continue")
      ContactNumberPage.verifyPageEnterContactNumberAndContinue("+44 808 157 0192")

      When("I verify ResidencePage select Yes and Continue")
      ResidencePage.verifyPageSelectNoAndContinue()

      When("I verify InternationalAddressPage enter address and Continue")
      InternationalAddressPage.verifyPageEnterAddressAndContinue()

      uniqueTaxSchemes.foreach { case (key, value) =>
        When("I verify LegacyPensionSchemeReferencePage enter reference and Continue")
        LegacyPensionSchemeReferencePage.verifyPageEnterReferenceAndContinue(key, value)

        When("I verify ReformPensionSchemeReferencePage enter reference and Continue")
        ReformPensionSchemeReferencePage.verifyPageEnterReferenceAndContinue(value)
      }

      When("I verify ClaimingAdditionalTaxRateRelief Page , select yes and click continue")
      ClaimingAdditionalTaxRateReliefPage.verifyPageClickYesAndContinue()

      When("I verify TaxReliefAmountPage Page, enter tax relief and click continue")
      TaxReliefAmountPage.verifyPageEnterTaxReliefAndContinue("999999999")

      When("I verify DeclarationsPage Page and click confirm")
      DeclarationsPage.verifyPageAndConfirm()

      CheckYourAnswersCalculationJourneyPage.clickSubmissionContinueButton()

      Then("I verify SubmissionPage Page")
      SubmissionPage.verifySubmissionPage()

      signOutPage()
    }
  }
}
