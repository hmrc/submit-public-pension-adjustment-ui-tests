package uk.gov.hmrc.test.ui.pages

import org.openqa.selenium.By
import uk.gov.hmrc.test.ui.constants.PageInformation.{WHICH_PENSION_SCHEME_WILL_PAY_TAX_RELIEF_PAGE_HEADER, WHICH_PENSION_SCHEME_WILL_PAY_TAX_RELIEF_PAGE_TITLE}

object WhichPensionSchemeWillPayTaxReliefPage extends BasePage {
  def verifyWhichPensionSchemeWillPayTaxReliefPage() = {
    verifyPageUrl("which-pension-scheme-will-pay-tax-relief")
    onPage(WHICH_PENSION_SCHEME_WILL_PAY_TAX_RELIEF_PAGE_TITLE)
    isHeader(WHICH_PENSION_SCHEME_WILL_PAY_TAX_RELIEF_PAGE_HEADER)
  }
  def selectPensionScheme(pensionScheme: String) =
    driver.findElement(By.xpath("//label[contains(text(),'" + pensionScheme + "')]/preceding::input[1]")).click()

  def verifyPageSelectPensionSchemeAndContinue(pensionScheme: String) = {
    verifyWhichPensionSchemeWillPayTaxReliefPage()
    selectPensionScheme(pensionScheme)
    submitPage()
  }
}
