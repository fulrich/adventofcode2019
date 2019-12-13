package grids

import ship.arcade.Sprite

case class Screen(sprites: Seq[Sprite], score: Int) {
  def updateScore(newScore: Long): Screen = copy(score = newScore.toInt)
  def + (sprite: Sprite): Screen = copy(sprites = sprites :+ sprite)

  def update(sprite: Sprite): Screen =
    copy(
      sprites = sprites
        .filter(currentSprite => currentSprite.x != sprite.x || currentSprite.y != sprite.y)
        .appended(sprite)
    )

  def updateScreen(output: Seq[Long]): Screen =
    output.sliding(3, step = 3).foldLeft(this) { case (screen, Seq(x, y, tile)) =>
      if(x == -1 && y == 0) screen.updateScore(tile)
      else screen.update(Sprite(x.toInt, y.toInt, tile))
    }
}

object Screen {
  val Empty: Screen = Screen(sprites = Vector.empty, score = 0)

  def from(output: Seq[Long]): Screen = Screen.Empty.updateScreen(output)
}