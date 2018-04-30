package noukenolife.ddd.domain.api.lifecycle.future

import noukenolife.ddd.domain.api.lifecycle.{IOContext, IOResult}

import scala.concurrent.{ExecutionContext, Future}

class FakeIOResult[A](value: => A)(implicit ec: ExecutionContext) extends IOResult[A, Future] {
  override def result()(implicit ctx: IOContext): Future[A] = Future(value)
  override def map[B](f: A => B)(implicit ctx: IOContext): IOResult[B, Future] = FakeIOResult(f(value))
  override def flatMap[B](f: A => IOResult[B, Future])(implicit ctx: IOContext): IOResult[B, Future] = f(value)
}

object FakeIOResult {
  def apply[A](value: => A)(implicit ec: ExecutionContext): FakeIOResult[A] = new FakeIOResult(value)
}
