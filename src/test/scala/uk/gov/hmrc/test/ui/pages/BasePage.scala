/*
 * Copyright 2023 HM Revenue & Customs
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
import org.scalatest.Assertion
import org.scalatest.matchers.should.Matchers
import uk.gov.hmrc.test.ui.conf.TestConfiguration
import uk.gov.hmrc.test.ui.driver.BrowserDriver

trait BasePage extends BrowserDriver with Matchers {
  val continueButton = "continue-button"

  def continuePage(): Unit =
    driver.findElement(By.id(continueButton)).click()

  def clickContinueButton(): Unit =
    driver.findElement(By.xpath("//a[contains(text(),'Continue')]")).click()
  def submitPage(): Unit          =
    driver.findElement(By.xpath("//button[contains(text(),'Continue')]")).click()
  def selectYesOption(): Unit     =
    driver.findElement(By.id("value")).click()

  def selectNoOption(): Unit =
    driver.findElement(By.id("value-no")).click()

  def onPage(pageTitle: String): Unit =
    if (driver.getTitle != pageTitle)
      throw PageNotFoundException(
        s"Expected '$pageTitle' page, but found '${driver.getTitle}' page."
      )

  def isHeader(header: String): Boolean = {
    var headerText = driver.findElement(By.xpath("//h1")).getText
    if (driver.findElements(By.xpath("//h1/span")).size() != 0) {
      headerText = headerText.replaceAll(driver.findElement(By.xpath("(//h1/span)")).getText, "").replaceAll("\n", "")
      if (headerText != header)
        throw PageNotFoundException(
          s"Expected '$header', but found '$headerText'"
        )
      else true
    }
    if ((driver.findElements(By.xpath("//h1/label/span")).size() != 0)) {
      headerText =
        headerText.replaceAll(driver.findElement(By.xpath("(//h1/label/span)")).getText, "").replaceAll("\n", "")
      if (headerText != header)
        throw PageNotFoundException(
          s"Expected '$header', but found '$headerText'"
        )
      else true
    } else {
      if (headerText != header)
        throw PageNotFoundException(
          s"Expected '$header', but found '$headerText'"
        )
      else true
    }
  }

  def clearDate() = {
    driver.findElement(By.id("value.day")).clear()
    driver.findElement(By.id("value.month")).clear()
    driver.findElement(By.id("value.year")).clear()
  }

  def enterDay(day: String)     =
    driver.findElement(By.id("value.day")).sendKeys(day)
  def enterMonth(month: String) =
    driver.findElement(By.id("value.month")).sendKeys(month)
  def enterYear(year: String)   =
    driver.findElement(By.id("value.year")).sendKeys(year)
  def verifyPageUrl(name: String): Assertion = {
    val currentUrl: String = driver.getCurrentUrl
    val lastPart           = currentUrl.replaceAll(TestConfiguration.url("ui-frontend") + "/", "")
    lastPart shouldEqual name
  }
}

case class PageNotFoundException(s: String) extends Exception(s)
