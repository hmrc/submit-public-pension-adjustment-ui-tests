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

package util

import play.api.libs.json.{JsError, JsResult, JsSuccess, Json}
import play.api.libs.ws.StandaloneWSResponse
import uk.gov.hmrc.test.ui.client.HttpClient
import uk.gov.hmrc.test.ui.conf.TestConfiguration
import uk.gov.hmrc.test.ui.pages._

import java.util.UUID
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

  def submitUserAnswersPostRequest(jsonBody: String): StandaloneWSResponse =
    Await.result(
      post(
        calculateBackendUrl + "/test-only/user-answers",
        jsonBody,
        ("Content-Type", "application/json"),
        ("CorrelationId", "12345671"),
        ("Accept", "application/vnd.hmrc.P1.0+json")
      ),
      10.seconds
    )
  def submitCalculation(fileName: String) = {
    val calculationSessionId = "Int-" + UUID.randomUUID().toString
    val calculationUniqueId  = UUID.randomUUID().toString

    val userAnswersStream           = getClass.getResourceAsStream("/UserAnswersStub/UserAnswers_Request.json")
    val userAnswersString           = scala.io.Source.fromInputStream(userAnswersStream).mkString
    val userAnswersCompletedRequest = userAnswersString
      .replaceAll("calculationSessionId", calculationSessionId)
      .replaceAll("calculationUniqueId", calculationUniqueId)
    submitUserAnswersPostRequest(userAnswersCompletedRequest)

    val requestStream              = getClass.getResourceAsStream("/calculateStub/" + fileName + "_Request.json")
    val jsonString                 = scala.io.Source.fromInputStream(requestStream).mkString
    val completedRequest           = jsonString
      .replaceAll("calculationSessionId", calculationSessionId)
      .replaceAll("calculationUniqueId", calculationUniqueId)
    val json                       = Json.parse(calculateSubmissionPostRequest(completedRequest).body)
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
