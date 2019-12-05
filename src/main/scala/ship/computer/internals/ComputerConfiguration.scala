package ship.computer.internals

import ship.computer.internals.instructions.io.{InputSource, OutputSource}


case class ComputerConfiguration(
  inputSource: InputSource,
  outputSource: OutputSource
)

object ComputerConfiguration {
  val Default: ComputerConfiguration = ComputerConfiguration(
    InputSource.StandardIn,
    OutputSource.StandardOut
  )
}
