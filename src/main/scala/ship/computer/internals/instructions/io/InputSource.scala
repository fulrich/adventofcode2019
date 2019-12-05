package ship.computer.internals.instructions.io

import scala.io.StdIn


trait InputSource {
  def get(): Int
}

object InputSource {
  val StandardIn: InputSource = () => StdIn.readInt
}
