package uk.gov.hmrc.test.ui.pages

import uk.gov.hmrc.test.ui.constants.PageInformation.{SUBMISSION_PAGE_HEADER, SUBMISSION_PAGE_TITLE}

object SubmissionPage extends BasePage {
  def verifySubmissionPage() = {
    verifyPageUrl("submission")
    onPage(SUBMISSION_PAGE_TITLE)
    isHeader(SUBMISSION_PAGE_HEADER)
  }
}
