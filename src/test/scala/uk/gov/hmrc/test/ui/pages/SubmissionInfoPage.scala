package uk.gov.hmrc.test.ui.pages

import uk.gov.hmrc.test.ui.constants.PageInformation.{SUBMISSION_INFO_PAGE_HEADER, SUBMISSION_INFO_PAGE_TITLE}

object SubmissionInfoPage extends BasePage {
  def verifySubmissionInfoPageAndContinue() = {
    onPage(SUBMISSION_INFO_PAGE_TITLE)
    isHeader(SUBMISSION_INFO_PAGE_HEADER)
    clickContinueButton()
  }
}
