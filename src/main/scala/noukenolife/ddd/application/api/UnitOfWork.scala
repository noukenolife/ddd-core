package noukenolife.ddd.application.api

import noukenolife.ddd.domain.api.lifecycle.IOContext

import scala.language.higherKinds

trait UnitOfWork[I[_], O[_]] {

  /**
    * Run a work
    *
    * @param f a work using IOContext
    * @tparam R result type
    * @return result
    */
  def run[R](f: IOContext => I[R]): O[R]
}
