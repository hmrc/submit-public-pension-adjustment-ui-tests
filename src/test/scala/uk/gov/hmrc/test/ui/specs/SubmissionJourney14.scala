package uk.gov.hmrc.test.ui.specs

import org.scalatest.BeforeAndAfter
import uk.gov.hmrc.test.ui.pages.HomePage.signOutPage
import uk.gov.hmrc.test.ui.pages._
import uk.gov.hmrc.test.ui.specs.tags.ZapTests
import util.CalculationDataUtil

import scala.collection.mutable
class SubmissionJourney14 extends BaseSpec with BeforeAndAfter {
  var uniqueTaxSchemes: mutable.Map[String, String] = mutable.Map.empty[String, String]
  before {
    val calculationData = new CalculationDataUtil()
    calculationData.submitCalculation("calculationDataSet13")

    /** add scheme details from the test json to below map * */
    uniqueTaxSchemes += ("Scheme 1" -> "00348916RU")
  }

    Feature("PRA Submission Journey 4, Business scenario AA journeys") {

      Scenario(s"PRA Scenario 4", ZapTests) {

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

        When("I verify DeclarationsPage Page and click confirm")
        DeclarationsPage.verifyPageAndConfirm()

        CheckYourAnswersCalculationJourneyPage.clickSubmissionContinueButton()

        Then("I verify SubmissionPage Page")
        SubmissionPage.verifySubmissionPage()

        signOutPage()
      }
    }

}




/** User claiming on behalf of somebody else, deputyship, date of death, non-UK resident, no alternate name, UK resident, higher rate relief not claimed
Claiming on behalf = Y
PoA/Deputy = Deputyship
Name = John Smith
Their DoB = 1/1/1960
Their DoD = 1/1/2023
Their NINO = AA1234567B
UTR = 123456767890
UK resident = N
Non-UK address = 1 Street, Dublin, Ireland
Alternate name = Y
Contact number = 07884554867
UK resident = Y
UK address = 1 Road, London, AB1 2CD
Legacy ref 1 = 123456789
Reform ref 1 = 123456781
Higher rate = N
*/