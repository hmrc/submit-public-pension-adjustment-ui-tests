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

import util.DataCollectorMap

object WhatYouWillNeedAaPage extends BasePage {
  def onWhatYouWillNeedAa2016PrePage() = {
    DataCollectorMap.checkAnswersAAPeriod = List.empty[(String, Any)]
    clickContinueButton()
  }
  def onWhatYouWillNeedAa2016PostPage() = {
    DataCollectorMap.checkAnswersAAPeriod = List.empty[(String, Any)]
    clickContinueButton()
  }
  def onWhatYouWillNeedAa2017Page() = {
    DataCollectorMap.checkAnswersAAPeriod = List.empty[(String, Any)]
    clickContinueButton()
  }
  def onWhatYouWillNeedAa2018Page() = {
    DataCollectorMap.checkAnswersAAPeriod = List.empty[(String, Any)]
    clickContinueButton()
  }
  def onWhatYouWillNeedAa2019Page() = {
    DataCollectorMap.checkAnswersAAPeriod = List.empty[(String, Any)]
    clickContinueButton()
  }
  def onWhatYouWillNeedAa2020Page() = {
    DataCollectorMap.checkAnswersAAPeriod = List.empty[(String, Any)]
    clickContinueButton()
  }
  def onWhatYouWillNeedAa2021Page() =
    clickContinueButton()
  def onWhatYouWillNeedAa2022Page() =
    clickContinueButton()
  def onWhatYouWillNeedAa2023Page() =
    clickContinueButton()

}
