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
import uk.gov.hmrc.test.ui.conf.TestConfiguration
import uk.gov.hmrc.test.ui.constants.PageInformation.{HOME_PAGE_HEADER, HOME_PAGE_TITLE, THEIR_NINO_TRN_HEADER, THEIR_NINO_TRN_TITLE, THEIR_UTR_HEADER, THEIR_UTR_TITLE}
import uk.gov.hmrc.test.ui.pages.HomePage.{driver, onPage, url}
object TheirUTRPage extends BasePage {

  def verifyTheirUTRPage() = {
    onPage(THEIR_UTR_TITLE)
    isHeader(THEIR_UTR_HEADER)
  }

  def enterUTR(): Unit =
    driver.findElement(By.id("value")).sendKeys("00348916RT")

  def verifyPageEnterUTRAndContinue(): Unit = {
    verifyTheirUTRPage()
    enterUTR()
    submitPage()
  }
}