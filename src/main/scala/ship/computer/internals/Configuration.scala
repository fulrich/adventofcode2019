package ship.computer.internals

import ship.computer.internals.instructions.io.{InputSource, OutputSource}


case class Configuration(
  inputSource: InputSource,
  outputSource: OutputSource
)

object Configuration {
  val Default: Configuration = Configuration(
    InputSource.StandardIn,
    OutputSource.StandardOut
  )
}
