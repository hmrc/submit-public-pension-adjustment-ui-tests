package uk.gov.hmrc.test.ui.pages

import uk.gov.hmrc.test.ui.constants.PageInformation.{CLAIMING_ADDITIONAL_TAX_RELIEF_PAGE_HEADER, CLAIMING_ADDITIONAL_TAX_RELIEF_PAGE_TITLE}

object ClaimingAdditionalTaxRateReliefPage extends BasePage {
  def verifyClaimingAdditionalTaxRateReliefPage() = {
    verifyPageUrl("claiming-additional-tax-rate-relief")
    onPage(CLAIMING_ADDITIONAL_TAX_RELIEF_PAGE_TITLE)
    isHeader(CLAIMING_ADDITIONAL_TAX_RELIEF_PAGE_HEADER)
  }

  def verifyPageClickYesAndContinue() = {
    driver.navigate().to("http://localhost:12805/submit-public-pension-adjustment/claiming-additional-tax-rate-relief")
    verifyClaimingAdditionalTaxRateReliefPage()
    selectYesOption()
    submitPage()
  }
}
