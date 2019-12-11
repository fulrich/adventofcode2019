package asteroidbelt.targeting

import asteroidbelt.{Asteroid, Slope}


case class Target(
  asteroid: Asteroid,
  slope: Slope,
  rotation: Int = 1
)
