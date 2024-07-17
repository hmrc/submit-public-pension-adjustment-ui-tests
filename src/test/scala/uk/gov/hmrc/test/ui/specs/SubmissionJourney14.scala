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
class SubmissionJourney14 extends BaseSpec with BeforeAndAfter {
  var uniqueTaxSchemes: Map[String, String] = Map(("Scheme 1" -> "00348916RU"))

  Feature("PRA Submission Journey 4, Business scenario AA journeys") {

    Scenario(s"PRA Scenario 4", ZapTests) {
      val calculationData = new CalculationDataUtil()
      calculationData.submitCalculation("calculationDataSet13")

      When("User landed to SubmissionInfo page ")
      SubmissionInfoPage.verifySubmissionInfoPageAndContinue()

      When("I verify ClaimOnBehalfPage, select yes and click continue button")
      ClaimOnBehalfPage.verifyPageSelectYesAndContinue()

      When("I verify StatusOfUserPage select deputyship and continue")
      StatusOfUserPage.verifyPageSelectDeputyshipAndContinue()

      When("I verify TheirNamePage enter pension scheme name and continue")
      TheirNamePage.verifyPageEnterPensionSchemeNameAndContinue()

      When("I verify TheirDOBPage enter date of birth and continue")
      TheirDOBPage.verifyPageEnterBirthdayAndContinue()

      When("I verify Their date of death Page enter date of death and continue")
      TheirDateOfDeathPage.verifyPageEnterDateOfDeathAndContinue()

      When("I verify TheirNinoPage enter NINO and continue")
      TheirNinoPage.verifyPageEnterNinoAndContinue()

      When("I verify TheirUTRPage enter tax reference and continue")
      TheirUTRPage.verifyPageEnterUTRAndContinue()

      When("I verify TheirResidencePage, select yes and continue")
      TheirResidencePage.verifyPageSelectNoAnContinue()

      When("I verify InternationalAddressPage, Enter Address information and continue")
      TheirInternationalAddressPage.verifyPageEnterAddressAndContinue()

      When("I verify AlternativeNamePage, select No and continue")
      AlternativeNamePage.verifyPageSelectYesAndContinue()

      When("I verify ContactNumberPage, enter phone number and continue")
      ContactNumberPage.verifyPageEnterContactNumberAndContinue("07884554867")

      When("I verify ResidencePage select Yes and Continue")
      ResidencePage.verifyPageSelectYesAndContinue()

      When("I verify InternationalAddressPage enter address and Continue")
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

      When("I verify SchemeCreditConsent, select agree and click continue")
      SchemeCreditConsentPage.verifyPageAgreeAndContinue()

      When("I verify DeclarationsPage Page and click confirm")
      DeclarationsPage.verifyPageAndConfirm()

      Then("I verify SubmissionPage Page")
      SubmissionPage.verifySubmissionPage()

      signOutPage()
    }
  }

}

/** User claiming on behalf of somebody else, deputyship, date of death, non-UK resident, no alternate name, UK resident, higher rate relief not claimed
  * Claiming on behalf = Y
  * PoA/Deputy = Deputyship
  * Name = John Smith
  * Their DoB = 1/1/1960
  * Their DoD = 1/1/2023
  * Their NINO = AA1234567B
  * UTR = 123456767890
  * UK resident = N
  * Non-UK address = 1 Street, Dublin, Ireland
  * Alternate name = Y
  * Contact number = 07884554867
  * UK resident = Y
  * UK address = 1 Road, London, AB1 2CD
  * Legacy ref 1 = 123456789
  * Reform ref 1 = 123456781
  * Higher rate = N
  */
