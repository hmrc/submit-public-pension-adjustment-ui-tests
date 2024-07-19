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

import scala.collection.mutable.ArrayBuffer
class SubmissionJourney15 extends BaseSpec with BeforeAndAfter {
  var uniqueTaxSchemes: Map[String, String] = Map(
    /** add scheme details from the test json to below map * */
    ("Scheme 1" -> "00348916RU"),
    ("Scheme 4" -> "00348916RC")
  )
  var debitYears: ArrayBuffer[Int]          = ArrayBuffer(
    /** add all the debit years(debit amount > 0) from the "calculate" section in the test json* */
    2020,
    2022,
    2023
  )

  Feature("PRA Submission Journey 5") {

    /** User has debit, member paying debit, no alternate name, UK resident, user not claiming higher rate relief, has credit */
    Scenario(s"PRA Submission Journey 5", ZapTests) {
      val calculationData = new CalculationDataUtil()
      calculationData.submitCalculation("calculationDataSet10")

      When("User landed to SubmissionInfo page ")
      SubmissionInfoPage.verifySubmissionInfoPageAndContinue()

      When("I verify ClaimOnBehalfPage, select yes and click continue button")
      ClaimOnBehalfPage.verifyPageSelectNoAndContinue()

      debitYears.foreach { element =>
        When("I verify WhoWillPayPage, select pension scheme and click continue button")
        WhoWillPayPage.verifyPageSelectYouAndContinue()
      }

      When("I verify AlternativeNamePage, select No and continue")
      AlternativeNamePage.verifyPageSelectYesAndContinue()

      When("I verify ContactNumberPage, enter phone number and continue")
      ContactNumberPage.verifyPageEnterContactNumberAndContinue("10101")

      When("I verify ResidencePage select Yes and Continue")
      ResidencePage.verifyPageSelectYesAndContinue()

      When("I verify UKAddressPage enter address and Continue")
      UKAddressPage.verifyPageEnterAddressAndContinue()

      uniqueTaxSchemes.foreach { case (key, value) =>
        When("I verify LegacyPensionSchemeReferencePage enter reference and Continue")
        LegacyPensionSchemeReferencePage.verifyPageEnterReferenceAndContinue(key, value)

        When("I verify ReformPensionSchemeReferencePage enter reference and Continue")
        ReformPensionSchemeReferencePage.verifyPageEnterReferenceAndContinue(value)
      }

      When("I verify ClaimingAdditionalTaxRateRelief Page , select No and click continue")
      ClaimingAdditionalTaxRateReliefPage.verifyPageClickNoAndContinue()

      CheckYourAnswersCalculationJourneyPage.clickSubmissionContinueButton()

      When("I verify DeclarationsPage Page and click confirm")
      DeclarationsPage.verifyPageAndConfirm()

      Then("I verify SubmissionPage Page")
      SubmissionPage.verifySubmissionPage()

      signOutPage()
    }
  }
}
