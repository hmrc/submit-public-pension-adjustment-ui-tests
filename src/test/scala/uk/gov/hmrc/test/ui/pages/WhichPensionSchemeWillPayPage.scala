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
import uk.gov.hmrc.test.ui.constants.PageInformation.{WHICH_PENSION_SCHEME_WILL_PAY_PAGE_HEADER, WHICH_PENSION_SCHEME_WILL_PAY_PAGE_TITLE}

object WhichPensionSchemeWillPayPage extends BasePage {
  def verifyWhichPensionSchemeWillPayPage() = {
    onPage(WHICH_PENSION_SCHEME_WILL_PAY_PAGE_TITLE)
    isHeader(WHICH_PENSION_SCHEME_WILL_PAY_PAGE_HEADER)
  }

  def selectPrivatePensionScheme() = driver.findElement(By.xpath("//input[@value='privatePensionScheme']")).click()

  def selectPensionScheme(pensionScheme: String) =
    driver.findElement(By.xpath("//input[@value='" + pensionScheme + "']")).click()

  def verifyPageSelectPrivatePSAndContinue() = {
    verifyWhichPensionSchemeWillPayPage()
    selectPrivatePensionScheme()
    submitPage()
  }

  def verifyPageSelectPSAndContinue(pensionScheme: String) = {
    verifyWhichPensionSchemeWillPayPage()
    selectPensionScheme(pensionScheme)
    submitPage()
  }
}