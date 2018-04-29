package noukenolife.ddd.domain.api.model

trait Id[T] {
  def value: T
}
