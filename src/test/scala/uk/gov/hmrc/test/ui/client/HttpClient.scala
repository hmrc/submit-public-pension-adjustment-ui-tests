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

package uk.gov.hmrc.test.ui.client

import akka.actor.ActorSystem
import play.api.libs.ws.DefaultBodyWritables._
import play.api.libs.ws._
import play.api.libs.ws.ahc.StandaloneAhcWSClient

import scala.concurrent.{ExecutionContext, Future}

trait HttpClient {

  implicit val actorSystem: ActorSystem = ActorSystem()
  val wsClient: StandaloneAhcWSClient   = StandaloneAhcWSClient()
  implicit val ec: ExecutionContext     = ExecutionContext.global

  def post(url: String, bodyAsJson: String, headers: (String, String)*): Future[StandaloneWSRequest#Self#Response] =
    wsClient
      .url(url)
      .withHttpHeaders(headers: _*)
      .post(bodyAsJson)

  def postWithFormData(
    url: String,
    paramData: Map[String, String],
    headers: (String, String)*
  ): Future[StandaloneWSRequest#Self#Response] =
    wsClient
      .url(url)
      .withHttpHeaders(headers: _*)
      .post(paramData.map { case (k, v) => s"$k=$v" }.mkString("&"))

}
