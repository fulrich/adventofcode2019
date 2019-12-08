package mars

import mars.spaceimage.SpaceImage

import scala.io.Source
import scala.util.Try


object RoverPassword {
  val SpaceImageWidth = 25
  val SpaceImageHeight = 6
  val SpaceImageResource = "mars/rover_password_image.txt"


  lazy val Image: Try[SpaceImage] = from(SpaceImageResource)

  def from(resourceName: String): Try[SpaceImage] =
    raw(resourceName).map(SpaceImageParser(SpaceImageWidth, SpaceImageHeight).from)

  def raw(resourceName: String): Try[String] = Try(Source.fromResource(resourceName).getLines.mkString)
}
