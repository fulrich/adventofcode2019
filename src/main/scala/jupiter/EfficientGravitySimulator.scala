package jupiter

import org.apache.commons.math3.util.ArithmeticUtils

import scala.annotation.tailrec


object EfficientGravitySimulator {
  def stepsToInitial(jupiter: JupiterOrbit): Long = {
    val xPositionPairs = jupiter.moons.map(moon => PositionPair(moon.position.x, moon.velocity.x))
    val yPositionPairs = jupiter.moons.map(moon => PositionPair(moon.position.y, moon.velocity.y))
    val zPositionPairs = jupiter.moons.map(moon => PositionPair(moon.position.z, moon.velocity.z))

    val stepToXInitial = stepsToInitial(xPositionPairs)
    val stepToYInitial = stepsToInitial(yPositionPairs)
    val stepToZInitial = stepsToInitial(zPositionPairs)

    ArithmeticUtils.lcm(
      ArithmeticUtils.lcm(
        stepToXInitial,
        stepToYInitial
      ),
      stepToZInitial
    )
  }

  def stepsToInitial(positions: Seq[PositionPair]): Long = stepsToInitial(simulate(positions), 1L)

  @tailrec
  private def stepsToInitial(positions: Seq[PositionPair], count: Long): Long = {
    if(positions.forall(_.velocity == 0)) count * 2
    else stepsToInitial(simulate(positions), count + 1)
  }

  def simulate(positions: Seq[PositionPair]): Seq[PositionPair] = updatedVelocities(positions).map(_.applyVelocity)

  def updatedVelocities(positions: Seq[PositionPair]): Seq[PositionPair] =
    positions.map { position =>
      positions.filter(_ != position).foldLeft(position) { case (updatedPosition, otherPosition) =>
        updatedPosition.applyGravity(otherPosition)
      }
    }
}
