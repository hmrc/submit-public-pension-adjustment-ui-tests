package uk.gov.hmrc.test.ui.pages

import org.openqa.selenium.By
import uk.gov.hmrc.test.ui.constants.PageInformation.{DECLARATION_PAGE_HEADER, DECLARATION_PAGE_TITLE}
import uk.gov.hmrc.test.ui.pages.HomePage.{driver, url}

object DeclarationsPage extends BasePage {
  def verifyDeclarationsPage() = {
    verifyPageUrl("declarations")
    onPage(DECLARATION_PAGE_TITLE)
    isHeader(DECLARATION_PAGE_HEADER)
  }

  def clickConfirmButton() = driver.findElement(By.xpath("//a[contains(text(),'Confirm')]")).click()

  def verifyPageAndConfirm() = {
    driver.navigate().to("http://localhost:12805/submit-public-pension-adjustment/declarations")
    verifyDeclarationsPage()
    clickConfirmButton()
  }

}
