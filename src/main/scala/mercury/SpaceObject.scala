package mercury

case class SpaceObject(code: String, orbitedBy: Seq[SpaceObject] = Vector.empty, indirectOrbits: Int = 0) {
  lazy val directOrbits: Int = if(code == "COM") 0 else 1
  lazy val orbits: Int = directOrbits + indirectOrbits + orbitedBy.map(_.orbits).sum

  def pathTo(searchCode: String): Seq[String] =
    if(searchCode == code) Vector(code)
    else if (orbitedBy.isEmpty) Vector.empty
    else {
      val orbitedSearch = orbitedBy.flatMap(_.pathTo(searchCode))
      if(orbitedSearch.nonEmpty) code +: orbitedSearch else Vector.empty
    }

  def print(tabbing: Int = 0): Unit = {
    val tabs = "".padTo(tabbing, ' ')
    println(s"$tabs($code:$indirectOrbits")
    orbitedBy.foreach(_.print(tabbing + 2))
    println(s"$tabs)")
  }
}
