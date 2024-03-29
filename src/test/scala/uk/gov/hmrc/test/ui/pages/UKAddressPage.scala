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

object UKAddressPage extends BasePage {

  def enterAddressInformation() = {
    driver.findElement(By.id("addressLine1")).sendKeys("No 137")
    driver.findElement(By.id("addressLine2")).sendKeys("Prince bcd road")
    driver.findElement(By.id("townOrCity")).sendKeys("London")
    driver.findElement(By.id("county")).sendKeys("London")
    driver.findElement(By.id("postCode")).sendKeys("AB3 7ED")
  }

  def enterNewAddressInformation(
    addressLine1: String,
    addressLine2: String,
    townOrCity: String,
    county: String,
    postCode: String
  ) = {
    driver.findElement(By.id("addressLine1")).sendKeys(addressLine1)
    driver.findElement(By.id("addressLine2")).sendKeys(addressLine2)
    driver.findElement(By.id("townOrCity")).sendKeys(townOrCity)
    driver.findElement(By.id("county")).sendKeys(county)
    driver.findElement(By.id("postCode")).sendKeys(postCode)
  }

  def verifyPageEnterAddressAndContinue() = {
    enterAddressInformation()
    submitPage()
  }

  def verifyPageEnterNewAddressAndContinue(
    addressLine1: String,
    addressLine2: String,
    townOrCity: String,
    county: String,
    postCode: String
  ) = {
    enterNewAddressInformation(
      addressLine1: String,
      addressLine2: String,
      townOrCity: String,
      county: String,
      postCode: String
    )
    submitPage()
  }

}
