package uk.gov.hmrc.test.ui.pages

import org.openqa.selenium.By
import uk.gov.hmrc.test.ui.conf.TestConfiguration
import uk.gov.hmrc.test.ui.utils.NINOGenerator

object AuthorityWizardPage extends BasePage {

  val authUrl: String = TestConfiguration.url("auth-frontend")

  def authorizedLoginUser(): Unit = {
    driver.navigate().to(authUrl)
    driver.findElement(By.id("redirectionUrl")).sendKeys(HomePage.url)
    driver.findElement(By.id("nino")).sendKeys(NINOGenerator.nino)
    driver.findElement(By.id("submit-top")).submit()
  }
}
