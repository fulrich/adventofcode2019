package ship.arcade

import grids.{GridPrinter, Screen}
import ship.computer.{IntcodeProgram, Programs}
import ship.computer.internals.Configuration
import ship.computer.internals.instructions.io.Output

import scala.annotation.tailrec


case class Cabinet(program: IntcodeProgram, screen: Screen) {
  lazy val ball: Sprite = screen.sprites.find(_.tile == Tile.Ball).get
  lazy val paddle: Sprite = screen.sprites.find(_.tile == Tile.Paddle).get

  lazy val donePlaying: Boolean = gameOver || gameWon
  lazy val  gameOver: Boolean = program.state.isComplete
  lazy val gameWon: Boolean = numberOf(Tile.Block) == 0

  def numberOf(tile: Int): Int = screen.sprites.count(_.tile == tile)

  def continue(input: Long): Cabinet = {
    val continuedProgram = program.setOutput(Output.Collection()).continue(input)
    copy(program = continuedProgram, screen = screen.updateScreen(continuedProgram.output))
  }

  def autoPlayInput(): Int = {
    if(ball.x < paddle.x) -1
    else if (ball.x > paddle.x) 1
    else 0
  }

  def autoPlay(): Cabinet = continue(autoPlayInput())

  def print(): Unit = {
    println("SCORE: " + screen.score)
    new GridPrinter[Sprite](screen.sprites)(identity[Sprite])(Sprite.print)
  }
}

object Cabinet {
  val GameProgram: IntcodeProgram = Programs.ArcadeGame.get

  def start(quarters: Int = 1, program: IntcodeProgram = GameProgram): Cabinet = {
    val startedProgram = GameProgram.set(0, quarters).configure(Configuration.singleInput).start()
    Cabinet(startedProgram, screen = Screen.from(startedProgram.output))
  }

  @tailrec
  def play(cabinet: Cabinet, print: Boolean = false): Cabinet = {
    if(print) { cabinet.print() }

    if(cabinet.donePlaying) cabinet
    else play(cabinet.autoPlay())
  }
}
