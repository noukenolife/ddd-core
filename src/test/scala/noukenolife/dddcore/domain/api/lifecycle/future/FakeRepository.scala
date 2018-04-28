package noukenolife.dddcore.domain.api.lifecycle.future

import noukenolife.dddcore.domain.api.lifecycle.{IOContext, EntityNotFoundException}
import noukenolife.dddcore.domain.api.model.{FakeEntity, FakeId}

import scala.concurrent.{ExecutionContext, Future}

class FakeRepository(var entityMap: Map[FakeId, FakeEntity] = Map.empty)
  (implicit val ec: ExecutionContext) extends Repository[FakeId, FakeEntity] {

  override def resolve(id: FakeId)(implicit ctx: IOContext): Future[FakeEntity] = Future {
    entityMap.getOrElse(id, throw EntityNotFoundException())
  }

  override def store(entity: FakeEntity)(implicit ctx: IOContext): Future[Unit] = Future {
    entityMap += (entity.id -> entity)
  }

  override def delete(id: FakeId)(implicit ctx: IOContext): Future[Unit] = Future {
    entityMap -= id
  }
}
