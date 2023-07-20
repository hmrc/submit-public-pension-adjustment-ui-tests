package uk.gov.hmrc.test.ui.pages

import org.openqa.selenium.By
import uk.gov.hmrc.test.ui.constants.PageInformation.{TAX_RELIEF_AMOUNT_PAGE_HEADER, TAX_RELIEF_AMOUNT_PAGE_TITLE}

object TaxReliefAmountPage extends BasePage {
  def verifyTaxReliefAmountPage() = {
    verifyPageUrl("tax-relief-amount")
    onPage(TAX_RELIEF_AMOUNT_PAGE_TITLE)
    isHeader(TAX_RELIEF_AMOUNT_PAGE_HEADER)
  }

  def enterTaxReliefAmount() = driver.findElement(By.id("value")).sendKeys("230000000")

  def verifyPageEnterTaxReliefAndContinue() = {
    verifyTaxReliefAmountPage()
    enterTaxReliefAmount()
    submitPage()
  }
}
