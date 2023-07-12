package uk.gov.hmrc.test.ui.pages

import uk.gov.hmrc.test.ui.constants.PageInformation.{CLAIM_ON_BEHALF_PAGE_HEADER, CLAIM_ON_BEHALF_PAGE_TITLE}

object ClaimOnBehalfPage extends BasePage {
  def verifyClaimOnBehalfPage() = {
    onPage(CLAIM_ON_BEHALF_PAGE_TITLE)
    isHeader(CLAIM_ON_BEHALF_PAGE_HEADER)
  }
  def verifyPageSelectYesAndContinue() = {
    verifyClaimOnBehalfPage()
    selectYesOption()
    submitPage()
  }
  def verifyPageSelectNoAndContinue() = {
    verifyClaimOnBehalfPage()
    selectNoOption()
    submitPage()
  }
}
