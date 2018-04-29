package noukenolife.ddd.domain.api.model

import org.scalatest.{Matchers, WordSpec}

class EntitySpec extends WordSpec with Matchers {

  case class AnotherFakeEntity(id: FakeId, value: String) extends Entity[FakeId]

  "An entity" must {
    "equals" in {
      FakeEntity(FakeId(1l), "Value1") shouldEqual FakeEntity(FakeId(1l), "Value2")
    }
    "not equal" in {
      FakeEntity(FakeId(1l), "Value1") shouldNot equal(FakeEntity(FakeId(2l), "Value1"))
      FakeEntity(FakeId(1l), "Value1") shouldNot equal(AnotherFakeEntity(FakeId(1l), "Value1"))
    }
  }
}
