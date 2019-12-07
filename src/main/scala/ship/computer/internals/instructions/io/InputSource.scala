package ship.computer.internals.instructions.io

import scala.collection.mutable
import scala.io.StdIn


trait InputSource {
  def get(): Int

}

object InputSource {
  val StandardIn: InputSource = () => StdIn.readInt

  case class ListInputSource(inputs: Seq[Int]) extends InputSource {
    private val inputValues: mutable.ArrayBuffer[Int] = mutable.ArrayBuffer.from(inputs)
    override def get(): Int = inputValues.remove(0)
  }
}
