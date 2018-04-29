package noukenolife.ddd.domain.api.model

trait Entity[I <: Id[_]] extends Equals {
  def id: I
  override def equals(obj: Any): Boolean = obj match {
    case e: Entity[_] => canEqual(obj) && id == e.id
    case _ => false
  }
  override def hashCode(): Int = 31 * id.value.##
}
