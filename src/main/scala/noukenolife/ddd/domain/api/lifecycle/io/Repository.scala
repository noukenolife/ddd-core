package noukenolife.ddd.domain.api.lifecycle.io

import cats.effect.IO
import noukenolife.ddd.domain.api.lifecycle.{Repository => BaseRepository}
import noukenolife.ddd.domain.api.model.{Entity, Id}

trait Repository[I <: Id[_], E <: Entity[I]] extends BaseRepository[I, E, IO]
