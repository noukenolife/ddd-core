package noukenolife.ddd.domain.api.lifecycle

import scala.language.higherKinds

trait IOResult[A, F[_]] {
  def result()(implicit ctx: IOContext): F[A]
  def map[B](f: A => B)(implicit ctx: IOContext): IOResult[B, F]
  def flatMap[B](f: A => IOResult[B, F])(implicit ctx: IOContext): IOResult[B, F]
}
