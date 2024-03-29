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

object ValueNewLtaChargePage extends BasePage {

  def enterNewLTAChargeMoreThanPreviousCharge() = {
    val text = "950000000"
    driver.findElement(By.id("value")).clear()
    driver.findElement(By.id("value")).sendKeys(text)
    checkYourAnswersLASMap(getHeader(), "£" + text)
  }

  def enterNewLTAChargeLessThanPreviousCharge() = {
    val text = "100000000"
    driver.findElement(By.id("value")).clear()
    driver.findElement(By.id("value")).sendKeys(text)
    checkYourAnswersLASMap(getHeader(), "£" + text)
  }

  def enterNewLTACharge() = {
    val text = "450000000"
    driver.findElement(By.id("value")).clear()
    driver.findElement(By.id("value")).sendKeys(text)
    checkYourAnswersLASMap(getHeader(), "£" + text)
  }

  def verifyPageEnterLTAChargeMoreThanPreviousChargeAndContinue() = {
    enterNewLTAChargeMoreThanPreviousCharge()
    submitPage()
  }

  def verifyPageEnterLTAChargeLessThanPreviousChargeAndContinue() = {
    enterNewLTAChargeLessThanPreviousCharge()
    submitPage()
  }

  def verifyPageEnterLTAChargeAndContinue() = {
    enterNewLTACharge()
    submitPage()
  }
}
