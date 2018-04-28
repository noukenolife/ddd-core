package noukenolife.dddcore.domain.api.lifecycle.io

import noukenolife.dddcore.domain.api.lifecycle.{IOContext, EntityNotFoundException, FakeIOContext}
import noukenolife.dddcore.domain.api.model.{FakeEntity, FakeId}
import org.scalatest.{Matchers, WordSpec}

class FakeRepositorySpec extends WordSpec with Matchers {

  implicit val ctx: IOContext = FakeIOContext
  val repo = new FakeRepository(Map(
    FakeId(1l) -> FakeEntity(FakeId(1l), "Value1")
  ))

  "A FakeIORepository" must {
    "resolve an entity" in {
      repo.resolve(FakeId(1l)).unsafeRunSync shouldEqual FakeEntity(FakeId(1l), "Value1")
    }
    "not resolve an entity" in {
      a[EntityNotFoundException] shouldBe thrownBy {
        repo.resolve(FakeId(3l)).unsafeRunSync
      }
    }
    "store a new entity" in {
      repo.store(FakeEntity(FakeId(2l), "Value2")).unsafeRunSync
      repo.entityMap shouldEqual Map(
        FakeId(1l) -> FakeEntity(FakeId(1l), "Value1"),
        FakeId(2l) -> FakeEntity(FakeId(2l), "Value2")
      )
    }
    "update an entity" in {
      repo.store(FakeEntity(FakeId(2l), "New Value2")).unsafeRunSync
      repo.entityMap shouldEqual Map(
        FakeId(1l) -> FakeEntity(FakeId(1l), "Value1"),
        FakeId(2l) -> FakeEntity(FakeId(2l), "New Value2")
      )
    }
    "delete an entity" in {
      repo.delete(FakeId(2l)).unsafeRunSync
      repo.entityMap shouldEqual Map(
        FakeId(1l) -> FakeEntity(FakeId(1l), "Value1")
      )
    }
  }
}
