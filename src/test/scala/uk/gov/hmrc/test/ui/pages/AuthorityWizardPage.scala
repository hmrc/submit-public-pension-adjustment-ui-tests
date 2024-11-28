/*
 * Copyright 2024 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.gov.hmrc.test.ui.pages

import org.openqa.selenium.By
import org.openqa.selenium.support.ui.Select
import uk.gov.hmrc.test.ui.conf.TestConfiguration
import util.NINOGenerator

object AuthorityWizardPage extends BasePage {

  val authUrl: String   = TestConfiguration.url("auth-frontend")
  val submitFrontendUrl = TestConfiguration.url("submit-ui-frontend")

  def authorizedLoginUser(): Unit = {
    driver.get(driver.getCurrentUrl)
    driver.findElement(By.id("nino")).sendKeys(NINOGenerator.generateNINO)
    selectConfidenceLevel("250")
    driver.findElement(By.id("add-preset")).click()
    driver.findElement(By.id("input-4-0-value")).sendKeys("123456789")
    driver.findElement(By.id("itmp.givenName")).sendKeys("Lari")
    driver.findElement(By.id("itmp.middleName")).sendKeys("Tharu")
    driver.findElement(By.id("itmp.familyName")).sendKeys("Jonson")
    driver.findElement(By.id("itmp.dateOfBirth")).sendKeys("1948-08-02")
    driver.findElement(By.id("submit-top")).submit()
  }

  def authorizedLoginUser(uniqueId: String): Unit = {
    HomePage.loadPage(authUrl)
    driver.findElement(By.id("nino")).sendKeys(NINOGenerator.generateNINO)
    selectConfidenceLevel("250")
    driver.findElement(By.id("add-preset")).click()
    driver.findElement(By.id("input-4-0-value")).sendKeys("123456789")
    driver.findElement(By.id("itmp.givenName")).sendKeys("Lari")
    driver.findElement(By.id("itmp.middleName")).sendKeys("Tharu")
    driver.findElement(By.id("itmp.familyName")).sendKeys("Jonson")
    driver.findElement(By.id("itmp.dateOfBirth")).sendKeys("1948-08-02")
    driver
      .findElement(By.id("redirectionUrl"))
      .sendKeys("http://localhost:12804/public-pension-adjustment/maybe-previous-claim-continue")
    driver.findElement(By.id("submit-top")).submit()
    Thread.sleep(2000)
  }

  def authorizedLoginUserSignOutAndSignInBack(uniqueId: String): Unit = {
    HomePage.loadPage(authUrl)
    val ninoToEnter = NINOGenerator.generateNINO
    driver.findElement(By.id("nino")).sendKeys(ninoToEnter)
    println("NINO: " + ninoToEnter)
    selectConfidenceLevel("250")
    driver.findElement(By.id("add-preset")).click()
    driver.findElement(By.id("input-4-0-value")).sendKeys("123456789")
    driver.findElement(By.id("itmp.givenName")).sendKeys("Lari")
    driver.findElement(By.id("itmp.middleName")).sendKeys("Tharu")
    driver.findElement(By.id("itmp.familyName")).sendKeys("Jonson")
    driver.findElement(By.id("itmp.dateOfBirth")).sendKeys("1948-08-02")
    driver
      .findElement(By.id("redirectionUrl"))
      .sendKeys(submitFrontendUrl + "/landing-page?submissionUniqueId=" + uniqueId)
    driver.findElement(By.id("submit-top")).submit()
    Thread.sleep(2000)
    driver.findElement(By.xpath("//a[contains(text(),'Next')]")).click()
    selectYesAndContinueCalculationsPage()
    driver.findElement(By.xpath("//nav/a[contains(text(),'Sign out')]")).click()
    driver.manage.deleteAllCookies()
    HomePage.loadPage(authUrl)
    driver.findElement(By.id("nino")).sendKeys(ninoToEnter)
    println("NINO: " + ninoToEnter)
    selectConfidenceLevel("250")
    driver.findElement(By.id("add-preset")).click()
    driver.findElement(By.id("input-4-0-value")).sendKeys("123456789")
    driver.findElement(By.id("itmp.givenName")).sendKeys("Lari")
    driver.findElement(By.id("itmp.middleName")).sendKeys("Tharu")
    driver.findElement(By.id("itmp.familyName")).sendKeys("Jonson")
    driver.findElement(By.id("itmp.dateOfBirth")).sendKeys("1948-08-02")
    driver
      .findElement(By.id("redirectionUrl"))
      .sendKeys(submitFrontendUrl + "/calculation-results")
    driver.findElement(By.id("submit-top")).submit()
  }

  def selectConfidenceLevel(confidenceLevel: String) = {
    val selectElement = driver.findElement(By.id("confidenceLevel"))
    val dropdown      = new Select(selectElement)
    dropdown.selectByValue(confidenceLevel)
  }
}
