package ship.arcade

import grids.PointLike

case class Sprite(x: Int, y: Int, tile: Long) extends PointLike

object Sprite {
  def print(sprite: Sprite): String = sprite.tile match {
    case Tile.Wall => "#"
    case Tile.Ball => "o"
    case Tile.Paddle => "_"
    case Tile.Block => "="
    case Tile.Empty => " "
  }
}
