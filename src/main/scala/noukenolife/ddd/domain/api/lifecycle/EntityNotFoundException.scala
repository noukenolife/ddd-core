package noukenolife.ddd.domain.api.lifecycle

/**
  * Entity not found exception
  *
  * @param message an exception message
  * @param cause a cause of the exception
  */
case class EntityNotFoundException(
  message: String = "",
  cause: Throwable = null
) extends Exception(message, cause)
