package mercury


case class OrbitRelationship(spaceObjectCode: String, orbitedByCode: String)

object OrbitRelationship {
  def parse(orbitRelationshipString: String): OrbitRelationship =
    orbitRelationshipString.split("\\)").toVector match {
      case Seq(spaceObjectCode, orbitedByCode) => OrbitRelationship(spaceObjectCode, orbitedByCode)
    }
}