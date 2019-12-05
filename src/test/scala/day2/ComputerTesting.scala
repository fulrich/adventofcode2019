package day2

import java.io.{ByteArrayOutputStream, StringReader}


trait ComputerTesting {
  def withInput[A](input: String)(test: => A): A =
    Console.withIn(new StringReader(input)) {
      test
    }

  def withOutput(test: => Unit): Seq[Int] = {
    val output = new ByteArrayOutputStream()

    Console.withOut(output) { test }

    output.toString.split('\n').map(_.trim).map(_.toInt)
  }

  def withIO(input: String)(test: => Unit): Seq[Int] = withInput(input) { withOutput(test) }
}
