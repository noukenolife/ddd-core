package noukenolife.dddcore.domain.api.lifecycle.future

import noukenolife.dddcore.domain.api.lifecycle.{Repository => BaseRepository}
import noukenolife.dddcore.domain.api.model.{Entity, Id}

import scala.concurrent.{ExecutionContext, Future}

trait Repository[I <: Id[_], E <: Entity[I]] extends BaseRepository[I, E, Future] {
  implicit def ec: ExecutionContext
}
