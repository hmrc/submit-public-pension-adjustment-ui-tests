package uk.gov.hmrc.test.ui.specs

import org.scalatest.BeforeAndAfter
import uk.gov.hmrc.test.ui.pages.HomePage.signOutPage
import uk.gov.hmrc.test.ui.pages._
import uk.gov.hmrc.test.ui.specs.tags.ZapTests
import util.CalculationDataUtil
import scala.collection.mutable

class SubmissionJourney16 extends BaseSpec with BeforeAndAfter{

  var uniqueTaxSchemes: mutable.Map[String, String] = mutable.Map.empty[String, String]
  before {
    val calculationData = new CalculationDataUtil()
    calculationData.submitCalculation("calculationDataSet12")

    /** add scheme details from the test json to below map * */
    uniqueTaxSchemes += ("Scheme 1" -> "123456789")
    uniqueTaxSchemes += ("Scheme 2" -> "123456789")
  }

  Feature("PRA SUbmission Journey 6") {

    /** User has no debit, no alternate name, UK resident, user claims higher rate relief, multiple schemes, no credit */
    Scenario(s"CPRA SUbmission Journey 6", ZapTests) {

      When("I verify ClaimOnBehalfPage, select yes and click continue button")
      ClaimOnBehalfPage.verifyPageSelectNoAndContinue()

      When("I verify AlternativeNamePage, select Yes and continue")
      AlternativeNamePage.verifyPageSelectYesAndContinue()

      When("I verify ContactNumberPage, enter phone number and continue")
      ContactNumberPage.verifyPageEnterContactNumberAndContinue("07884554867")

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

      When("I verify ClaimingAdditionalTaxRateRelief Page , select yes and click continue")
      ClaimingAdditionalTaxRateReliefPage.verifyPageClickYesAndContinue()

      When("I verify TaxReliefAmountPage Page, enter tax relief and click continue")
      TaxReliefAmountPage.verifyPageEnterTaxReliefAndContinue("1000")

      When("I verify WhichSchemeWillPay Page, select scheme and click continue")
      WhichPensionSchemeWillPayPage.verifyPageSelectPSAndContinue("Scheme1")

      When("I verify DeclarationsPage Page and click confirm")
      DeclarationsPage.verifyPageAndConfirm()

      CheckYourAnswersCalculationJourneyPage.clickSubmissionContinueButton()

      Then("I verify SubmissionPage Page")
      SubmissionPage.verifySubmissionPage()

      signOutPage()
    }
  }


}

/** User has no debit, no alternate name, UK resident, user claims higher rate relief, multiple schemes, no credit
Claiming on behalf = N
(User has no debit)
Alternate name = Y
Contact number = 07884554867
UK resident = Y
UK address = 1 Road, London, AB1 2CD
Legacy ref 1 = 123456789
Reform ref 1 = 123456781
Legacy ref 2 = 123456782
Reform ref 2 = 123456783
Higher rate relief = Y
Value claimed = 1000
(Multiple schemes entered)
Select scheme = NHS
(User has no credit)
*/