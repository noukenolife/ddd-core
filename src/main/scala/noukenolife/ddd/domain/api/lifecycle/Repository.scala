package noukenolife.ddd.domain.api.lifecycle

import noukenolife.ddd.domain.api.model.{Entity, Id}

import scala.language.higherKinds

/**
  * Repository API
  *
  * @tparam I entity id type
  * @tparam E entity type
  * @tparam M result monad
  */
trait Repository[I <: Id[_], E <: Entity[I], M[_]] {

  /**
    * Resolve by id
    *
    * @param id an entity id
    * @param ctx IO context
    * @return a resolved entity
    */
  def resolve(id: I)(implicit ctx: IOContext): M[E]

  /**
    * Store an entity
    *
    * @param entity an entity
    * @param ctx IO context
    * @return
    */
  def store(entity: E)(implicit ctx: IOContext): M[Unit]

  /**
    * Delete an entity by id
    *
    * @param id am entity id
    * @param ctx IO context
    * @return
    */
  def delete(id: I)(implicit ctx: IOContext): M[Unit]
}
