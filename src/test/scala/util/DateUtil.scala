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

package util

import java.time.LocalDate
import java.time.format.DateTimeFormatter

object DateUtil {
  def formatDate(inputDate: String, daysToAdd: Int): String = {
    val formatter       = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val date            = LocalDate.parse(inputDate, formatter)
    val modifiedDate    = date.plusDays(daysToAdd)
    val outputFormatter = DateTimeFormatter.ofPattern("d MMMM yyyy")
    modifiedDate.format(outputFormatter)
  }

}