package noukenolife.ddd.domain.api.lifecycle

/**
  * Repository exception
  *
  * @param message an exception message
  * @param cause a cause of the exception
  */
case class RepositoryException(
  message: String = "",
  cause: Throwable = null
) extends Exception(message, cause)
