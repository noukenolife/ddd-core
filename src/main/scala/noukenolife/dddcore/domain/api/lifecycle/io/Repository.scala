package noukenolife.dddcore.domain.api.lifecycle.io

import cats.effect.IO
import noukenolife.dddcore.domain.api.model.{Entity, Id}
import noukenolife.dddcore.domain.api.lifecycle.{Repository => BaseRepository}

trait Repository[I <: Id[_], E <: Entity[I]] extends BaseRepository[I, E, IO]
