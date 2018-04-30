package noukenolife.ddd.domain.api.lifecycle.io

import cats.effect.IO
import noukenolife.ddd.domain.api.lifecycle.{IOContext, IOResult}

class FakeIOResult[A](value: => A) extends IOResult[A, IO] {
  override def result()(implicit ctx: IOContext): IO[A] = IO(value)
  override def map[B](f: A => B)(implicit ctx: IOContext): IOResult[B, IO] = FakeIOResult(f(value))
  override def flatMap[B](f: A => IOResult[B, IO])(implicit ctx: IOContext): IOResult[B, IO] = f(value)
}

object FakeIOResult {
  def apply[A](value: => A): FakeIOResult[A] = new FakeIOResult(value)
}
