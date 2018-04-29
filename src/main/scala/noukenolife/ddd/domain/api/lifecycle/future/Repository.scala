package noukenolife.ddd.domain.api.lifecycle.future

import noukenolife.ddd.domain.api.model.{Entity, Id}
import noukenolife.ddd.domain.api.lifecycle.{Repository => BaseRepository}

import scala.concurrent.{ExecutionContext, Future}

trait Repository[I <: Id[_], E <: Entity[I]] extends BaseRepository[I, E, Future] {
  implicit def ec: ExecutionContext
}
