package noukenolife.ddd.domain.api.lifecycle.io

import cats.effect.IO
import noukenolife.ddd.domain.api.lifecycle.{EntityNotFoundException, IOContext, IOResult}
import noukenolife.ddd.domain.api.model.{FakeEntity, FakeId}

class FakeRepository(var entityMap: Map[FakeId, FakeEntity] = Map.empty) extends Repository[FakeId, FakeEntity] {

  override def resolve(id: FakeId)(implicit ctx: IOContext): IOResult[FakeEntity, IO] = FakeIOResult {
    entityMap.getOrElse(id, throw EntityNotFoundException())
  }

  override def store(entity: FakeEntity)(implicit ctx: IOContext): IOResult[Unit, IO] = FakeIOResult {
    entityMap += (entity.id -> entity)
  }

  override def delete(id: FakeId)(implicit ctx: IOContext): IOResult[Unit, IO] = FakeIOResult {
    entityMap -= id
  }
}
