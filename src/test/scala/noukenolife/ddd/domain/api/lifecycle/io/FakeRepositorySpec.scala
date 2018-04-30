package noukenolife.ddd.domain.api.lifecycle.io

import noukenolife.ddd.domain.api.lifecycle.{EntityNotFoundException, FakeIOContext, IOContext}
import noukenolife.ddd.domain.api.model.{FakeEntity, FakeId}
import org.scalatest.{Matchers, WordSpec}

class FakeRepositorySpec extends WordSpec with Matchers {

  implicit val ctx: IOContext = FakeIOContext
  val repo = new FakeRepository(Map(
    FakeId(1l) -> FakeEntity(FakeId(1l), "Value1")
  ))

  "A FakeIORepository" must {
    "resolve an entity" in {
      repo.resolve(FakeId(1l)).result.unsafeRunSync shouldEqual FakeEntity(FakeId(1l), "Value1")
    }
    "not resolve an entity" in {
      a[EntityNotFoundException] shouldBe thrownBy {
        repo.resolve(FakeId(3l)).result.unsafeRunSync
      }
    }
    "store a new entity" in {
      repo.store(FakeEntity(FakeId(2l), "Value2")).result.unsafeRunSync
      repo.entityMap shouldEqual Map(
        FakeId(1l) -> FakeEntity(FakeId(1l), "Value1"),
        FakeId(2l) -> FakeEntity(FakeId(2l), "Value2")
      )
    }
    "update an entity" in {
      repo.store(FakeEntity(FakeId(2l), "New Value2")).result.unsafeRunSync
      repo.entityMap shouldEqual Map(
        FakeId(1l) -> FakeEntity(FakeId(1l), "Value1"),
        FakeId(2l) -> FakeEntity(FakeId(2l), "New Value2")
      )
    }
    "delete an entity" in {
      repo.delete(FakeId(2l)).result.unsafeRunSync
      repo.entityMap shouldEqual Map(
        FakeId(1l) -> FakeEntity(FakeId(1l), "Value1")
      )
    }
  }
}
