package noukenolife.ddd.domain.api.lifecycle

import noukenolife.ddd.domain.api.model.{Entity, Id}

import scala.language.higherKinds

/**
  * Repository API
  *
  * @tparam I entity id type
  * @tparam E entity type
  */
trait Repository[I <: Id[_], E <: Entity[I], F[_]] {

  /**
    * Resolve by id
    *
    * @param id an entity id
    * @param ctx IO context
    * @return a resolved entity
    */
  def resolve(id: I)(implicit ctx: IOContext): IOResult[E, F]

  /**
    * Store an entity
    *
    * @param entity an entity
    * @param ctx IO context
    * @return
    */
  def store(entity: E)(implicit ctx: IOContext): IOResult[Unit, F]

  /**
    * Delete an entity by id
    *
    * @param id am entity id
    * @param ctx IO context
    * @return
    */
  def delete(id: I)(implicit ctx: IOContext): IOResult[Unit, F]
}
