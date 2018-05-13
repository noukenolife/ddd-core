package noukenolife.ddd.application.api

import noukenolife.ddd.application.api.dto.{Input, Output}

import scala.language.higherKinds

trait AppService[I <: Input, O <: Output, M[_]] {
  def invoke(input: I): M[O]
}
