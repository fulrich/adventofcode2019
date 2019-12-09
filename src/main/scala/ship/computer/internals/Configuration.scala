package ship.computer.internals

import ship.computer.internals.instructions.io.{Input, Output}


case class Configuration(
  input: Input,
  output: Output
)

object Configuration {
  val Default: Configuration = Configuration(
    Input.Console,
    Output.Console
  )

  def static(inputs: Seq[Long]): Configuration = Configuration(
    Input.Static(inputs),
    Output.Collection()
  )

  def singleInput: Configuration = Configuration(
    Input.NeedInput,
    Output.Collection()
  )

  def singleInput(nextInput: Long): Configuration = Configuration(
    Input.NextInput(nextInput),
    Output.Collection()
  )
}
