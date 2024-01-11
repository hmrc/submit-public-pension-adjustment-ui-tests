package uk.gov.hmrc.test.ui.specs

import org.scalatest.BeforeAndAfter
import uk.gov.hmrc.test.ui.pages.HomePage.signOutPage
import uk.gov.hmrc.test.ui.pages._
import util.CalculationDataUtil

import scala.collection.mutable

class SubmissionJourney12 extends BaseSpec with BeforeAndAfter {
  var uniqueTaxSchemes: mutable.Map[String, String] = mutable.Map.empty[String, String]
  var debitYears: mutable.ArrayBuffer[Int] = mutable.ArrayBuffer.empty[Int]
  before {
    val calculationData = new CalculationDataUtil()
    calculationData.submitCalculation("calculationDataSet11")

    /** add scheme details from the test json to below map * */
    uniqueTaxSchemes += ("Scheme 1" -> "00348916RU")
    uniqueTaxSchemes += ("Scheme 2" -> "00348916RG")

    /** add all the debit years(debit amount > 0) from the "calculate" section in the test json* */
    debitYears += 2020
  }

  Feature("PRA Submission Journey 2, Business scenario AA journeys") {

    /** User has debit, public scheme to pay, date of election known, no alternate name, UK resident, not claiming higher rate relief
     */
    /** 5.2(N debit), 5.10(Scheme paid), 5.11(public), ..... */

    Scenario("Calculate PRA Submission Journey 2") {

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

      When("I verify DeclarationsPage Page and click confirm")
      DeclarationsPage.verifyPageAndConfirm()

      CheckYourAnswersCalculationJourneyPage.clickSubmissionContinueButton()

      Then("I verify SubmissionPage Page")
      SubmissionPage.verifySubmissionPage()

      signOutPage()


    }
  }
}
