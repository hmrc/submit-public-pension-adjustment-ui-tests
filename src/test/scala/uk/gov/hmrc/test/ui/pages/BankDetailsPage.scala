package uk.gov.hmrc.test.ui.pages

import org.openqa.selenium.By
import uk.gov.hmrc.test.ui.constants.PageInformation.{BANK_DETAILS_PAGE_HEADER, BANK_DETAILS_PAGE_TITLE}

object BankDetailsPage extends BasePage {

  def verifyBankDetails() = {
    verifyPageUrl("bank-details")
    onPage(BANK_DETAILS_PAGE_TITLE)
    isHeader(BANK_DETAILS_PAGE_HEADER)
  }

  def enterBankDetails() = {
    driver.findElement(By.id("accountName")).sendKeys("Teddy Dickson")
    driver.findElement(By.id("sortCode")).sendKeys("207102")
    driver.findElement(By.id("accountNumber")).sendKeys("44311655")
  }

  def verifyPageEnterBankDetailsClickContinue()={
    verifyBankDetails()
    enterBankDetails()
    submitPage()
  }

}