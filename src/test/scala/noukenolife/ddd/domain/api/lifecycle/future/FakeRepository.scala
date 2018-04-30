package noukenolife.ddd.domain.api.lifecycle.future

import noukenolife.ddd.domain.api.lifecycle.{EntityNotFoundException, IOContext, IOResult}
import noukenolife.ddd.domain.api.model.{FakeEntity, FakeId}

import scala.concurrent.{ExecutionContext, Future}

class FakeRepository(var entityMap: Map[FakeId, FakeEntity] = Map.empty)
  (implicit val ec: ExecutionContext) extends Repository[FakeId, FakeEntity] {

  override def resolve(id: FakeId)(implicit ctx: IOContext): IOResult[FakeEntity, Future] = FakeIOResult {
    entityMap.getOrElse(id, throw EntityNotFoundException())
  }

  override def store(entity: FakeEntity)(implicit ctx: IOContext): IOResult[Unit, Future] = FakeIOResult {
    entityMap += (entity.id -> entity)
  }

  override def delete(id: FakeId)(implicit ctx: IOContext): IOResult[Unit, Future] = FakeIOResult {
    entityMap -= id
  }
}
