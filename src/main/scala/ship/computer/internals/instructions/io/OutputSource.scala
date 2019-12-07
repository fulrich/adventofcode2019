package ship.computer.internals.instructions.io

import scala.collection.mutable


trait OutputSource {
  def output(value: Int): Unit
  def output(): Seq[Int] = Vector.empty
}

object OutputSource {
  val StandardOut: OutputSource = (value: Int) => println(value)

  case class ListOutputSource() extends OutputSource {
    private val outputValue: mutable.ArrayBuffer[Int] = new mutable.ArrayBuffer[Int]()

    override def output(value: Int): Unit = outputValue.addOne(value)
    override def output: Seq[Int] = outputValue.toVector
  }
}
