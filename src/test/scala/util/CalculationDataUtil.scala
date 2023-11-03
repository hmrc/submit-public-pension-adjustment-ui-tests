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

import play.api.libs.json.{JsError, JsResult, JsSuccess, Json}
import play.api.libs.ws.StandaloneWSResponse
import uk.gov.hmrc.test.ui.client.HttpClient
import uk.gov.hmrc.test.ui.conf.TestConfiguration
import uk.gov.hmrc.test.ui.pages._
import scala.concurrent.Await
import scala.concurrent.duration.DurationInt

class CalculationDataUtil extends HttpClient {

  val calculateBackendUrl: String                                            = TestConfiguration.url("ui-backend")
  def calculateSubmissionPostRequest(jsonBody: String): StandaloneWSResponse =
    Await.result(
      post(
        calculateBackendUrl + "/submission",
        jsonBody,
        ("Content-Type", "application/json"),
        ("CorrelationId", "12345678"),
        ("Accept", "application/vnd.hmrc.P1.0+json")
      ),
      10.seconds
    )
  def submitCalculation(fileName: String) = {
    val requestStream              = getClass.getResourceAsStream("/calculateStub/" + fileName + "_Request.json")
    val jsonString                 = scala.io.Source.fromInputStream(requestStream).mkString
    val json                       = Json.parse(calculateSubmissionPostRequest(jsonString).body)
    val uniqueId: JsResult[String] = (json \ "uniqueId").validate[String]
    var submissionId               = ""
    uniqueId match {
      case JsSuccess(name, _) =>
        submissionId = name
      case JsError(errors)    =>
        println("Error parsing JSON")
    }
    AuthorityWizardPage.authorizedLoginUser(submissionId)
  }
}
