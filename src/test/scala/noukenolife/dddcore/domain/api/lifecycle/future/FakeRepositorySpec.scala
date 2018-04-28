package noukenolife.dddcore.domain.api.lifecycle.future

import noukenolife.dddcore.domain.api.lifecycle.{IOContext, EntityNotFoundException, FakeIOContext}
import noukenolife.dddcore.domain.api.model.{FakeEntity, FakeId}
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.{Matchers, WordSpec}

import scala.concurrent.ExecutionContext.Implicits.global

class FakeRepositorySpec extends WordSpec with Matchers with ScalaFutures {

  implicit val ctx: IOContext = FakeIOContext
  val repo = new FakeRepository(Map(
    FakeId(1l) -> FakeEntity(FakeId(1l), "Value1")
  ))

  "A FakeFutureRepository" must {
    "resolve an entity" in {
      whenReady(repo.resolve(FakeId(1l)))(_ shouldEqual FakeEntity(FakeId(1l), "Value1"))
    }
    "not resolve an entity" in {
      whenReady(repo.resolve(FakeId(3l)).failed) {
        _ shouldBe a[EntityNotFoundException]
      }
    }
    "store a new entity" in {
      whenReady(repo.store(FakeEntity(FakeId(2l), "Value2"))) { r =>
        repo.entityMap shouldEqual Map(
          FakeId(1l) -> FakeEntity(FakeId(1l), "Value1"),
          FakeId(2l) -> FakeEntity(FakeId(2l), "Value2")
        )
      }
    }
    "update an entity" in {
      whenReady(repo.store(FakeEntity(FakeId(2l), "New Value2"))) { r =>
        repo.entityMap shouldEqual Map(
          FakeId(1l) -> FakeEntity(FakeId(1l), "Value1"),
          FakeId(2l) -> FakeEntity(FakeId(2l), "New Value2")
        )
      }
    }
    "delete an entity" in {
      whenReady(repo.delete(FakeId(2l))) { r =>
        repo.entityMap shouldEqual Map(
          FakeId(1l) -> FakeEntity(FakeId(1l), "Value1")
        )
      }
    }
  }
}
