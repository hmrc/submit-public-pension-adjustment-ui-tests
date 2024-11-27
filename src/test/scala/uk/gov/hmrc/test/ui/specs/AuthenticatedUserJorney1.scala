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

package uk.gov.hmrc.test.ui.specs

import uk.gov.hmrc.test.ui.pages.CalculationResultPage
import uk.gov.hmrc.test.ui.pages.HomePage.signOutPage
import uk.gov.hmrc.test.ui.specs.tags.ZapTests
import util.CalculationDataUtil

class AuthenticatedUserJorney1 extends BaseSpec {

  Feature("Authenticated User journeys") {

    /** MCSC-1181 coverage for Authenticated user returns to the service and selects 'Yes' that they would like to sign in */
    Scenario(s"Authenticated user Journey1", ZapTests) {
      val calculationData = new CalculationDataUtil()
      When("Authenticated user returns to the service and selects Yes that they would like to sign in")
      calculationData.submitCalculationAuthenticatedUser("calculationDataSet1")

      Then("User redirected to calculation-results page")
      assert(CalculationResultPage.getCalcResultsPageHeading() == "Overview of calculation results")

      signOutPage()
    }

  }

}
