package mercury

import ship.Module

import scala.io.Source
import scala.util.Try

object OrbitalMap {
  val CenterOfMass: SpaceObject = SpaceObject("COM")
  val OrbitalMapResource = "mercury/orbital_map.txt"


  def orbitalMap: Try[SpaceObject] = orbitalMap(OrbitalMapResource)

  def orbitalMap(resourceName: String): Try[SpaceObject] =
    Try(build(Source.fromResource(resourceName).getLines.toVector))



  def build(orbitalRelationships: Seq[String]): SpaceObject = {
    build2(orbitalRelationships.map(OrbitRelationship.parse).groupBy(_.spaceObjectCode))
  }

  def build2(relationships: Map[String, Seq[OrbitRelationship]], currentObject: SpaceObject = CenterOfMass, indirectOrbits: Int = 0): SpaceObject = {
    val orbitingRelationships = relationships.getOrElse(currentObject.code, Vector.empty)
    val spaceObjectsOrbiting = orbitingRelationships.map { relationship =>
      SpaceObject.apply(relationship.orbitedByCode, Vector.empty, indirectOrbits)
    }

    currentObject.copy(orbitedBy = spaceObjectsOrbiting.map(build2(relationships, _, indirectOrbits + 1)))
  }
}
