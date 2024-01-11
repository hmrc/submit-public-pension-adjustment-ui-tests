package uk.gov.hmrc.test.ui.specs

import org.scalatest.BeforeAndAfter
import uk.gov.hmrc.test.ui.pages.HomePage.signOutPage
import uk.gov.hmrc.test.ui.pages._
import uk.gov.hmrc.test.ui.specs.tags.ZapTests
import util.CalculationDataUtil

import scala.collection.mutable
class SubmissionJourney15 extends BaseSpec with BeforeAndAfter {
  var uniqueTaxSchemes: mutable.Map[String, String] = mutable.Map.empty[String, String]
  var debitYears: mutable.ArrayBuffer[Int]          = mutable.ArrayBuffer.empty[Int]
  before {
    val calculationData = new CalculationDataUtil()
    calculationData.submitCalculation("calculationDataSet10")

    /** add scheme details from the test json to below map * */
    uniqueTaxSchemes += ("Scheme 1" -> "00348916RU")
    uniqueTaxSchemes += ("Scheme 4" -> "00348916RC")

    /** add all the debit years(debit amount > 0) from the "calculate" section in the test json* */
    debitYears += 2020
    debitYears += 2022
    debitYears += 2023
  }

  Feature("PRA Submission Journey 5") {

    /** User has debit, member paying debit, no alternate name, UK resident, user not claiming higher rate relief, has credit */
    Scenario(s"PRA Submission Journey 5", ZapTests) {

      When("I verify ClaimOnBehalfPage, select yes and click continue button")
      ClaimOnBehalfPage.verifyPageSelectNoAndContinue()

      debitYears.foreach { element =>
        When("I verify WhoWillPayPage, select pension scheme and click continue button")
        WhoWillPayPage.verifyPageSelectYouAndContinue()

//        When("I verify WhichPensionSchemeWillPayPage, select public pension scheme and click continue button")
//        WhichPensionSchemeWillPayPage.verifyPageSelectPSAndContinue("Scheme 1")
//
//        When("I verify Valid Election for Scheme to pay Page and select yes and click continue")
//        AskedPensionSchemeToPayTaxCharge.verifyPageSelectNoAndContinue()
//
//        When("I verify Estimated quarter of Election Page and select quarter and click continue")
//        WhenWillYouAskPensionSchemeToPay.verifyPageSelectQuarterAndContinue()
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

      When("I verify DeclarationsPage Page and click confirm")
      DeclarationsPage.verifyPageAndConfirm()

      CheckYourAnswersCalculationJourneyPage.clickSubmissionContinueButton()

      Then("I verify SubmissionPage Page")
      SubmissionPage.verifySubmissionPage()

      signOutPage()
    }
  }
}
