package mercury

object OrbitalPathFinder {
  def between(map: SpaceObject)(code1: String, code2: String): Seq[String] = {
    val firstPath = map.pathTo(code1)
    val secondPath = map.pathTo(code2)

    val intersections = firstPath.intersect(secondPath)
    val firstPathIntersections = intersections.map(firstPath.lastIndexOf(_)).max
    val secondPathIntersections = intersections.map(secondPath.lastIndexOf(_)).max

    firstPath.slice(firstPathIntersections, firstPath.length).reverse ++
      secondPath.slice(secondPathIntersections + 1, secondPath.length)
  }

  def minimumTransfersBetween(map: SpaceObject)(code1: String, code2: String): Int = {
    val pathBetween = between(map)(code1, code2)
    val transfersDontRequireEnds = pathBetween.init.tail

    transfersDontRequireEnds.length - 1
  }
}
