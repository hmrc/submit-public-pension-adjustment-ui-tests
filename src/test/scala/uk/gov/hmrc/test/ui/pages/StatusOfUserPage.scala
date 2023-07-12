package uk.gov.hmrc.test.ui.pages

import uk.gov.hmrc.test.ui.constants.PageInformation.{STATUS_OF_USER_PAGE_HEADER, STATUS_OF_USER_PAGE_TITLE, SUBMISSION_INFO_PAGE_HEADER, SUBMISSION_INFO_PAGE_TITLE}
import uk.gov.hmrc.test.ui.pages.SubmissionInfoPage.{isHeader, onPage, submitPage}

object StatusOfUserPage extends BasePage {
  def verifyStatusOfUserPage() = {
    onPage(STATUS_OF_USER_PAGE_TITLE)
    isHeader(STATUS_OF_USER_PAGE_HEADER)
  }
}
