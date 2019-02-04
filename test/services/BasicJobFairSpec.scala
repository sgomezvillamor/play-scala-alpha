package services

import org.scalatestplus.play._

class BasicJobFairSpec extends PlaySpec {

  "BasicJobFair" should {

    "work with a simple example" in {

      val engineersPreferences: Map[String, Seq[String]] = Map(
        "E1" -> Seq("P1", "P2", "P3"),
        "E2" -> Seq("P1", "P2", "P3"),
        "E3" -> Seq("P1", "P2", "P3")
      )
      val projectsPreferences: Map[String, Seq[String]] = Map(
        "P1" -> Seq("E1", "E2", "E3"),
        "P2" -> Seq("E2", "E3", "E1"),
        "P3" -> Seq("E3", "E1", "E2")
      )
      val projectsCapacities: Map[String, Int] = Map(
        "P1" -> 1,
        "P2" -> 1,
        "P3" -> 1
      )
      new BasicJobFair().matches(engineersPreferences, projectsPreferences, projectsCapacities).toSet must be(
        Set(ProjectMatching("P1", "E1"),
          ProjectMatching("P2", "E2"),
          ProjectMatching("P3", "E3")))
    }

    "work when all projects have same preferences" in {

      val engineersPreferences: Map[String, Seq[String]] = Map(
        "E1" -> Seq("P1", "P2", "P3"),
        "E2" -> Seq("P1", "P2", "P3"),
        "E3" -> Seq("P1", "P2", "P3")
      )
      val projectsPreferences: Map[String, Seq[String]] = Map(
        "P1" -> Seq("E1", "E2", "E3"),
        "P2" -> Seq("E1", "E2", "E3"),
        "P3" -> Seq("E1", "E2", "E3")
      )
      val projectsCapacities: Map[String, Int] = Map(
        "P1" -> 1,
        "P2" -> 1,
        "P3" -> 1
      )
      new BasicJobFair().matches(engineersPreferences, projectsPreferences, projectsCapacities).toSet must be(
        Set(ProjectMatching("P1", "E1"),
          ProjectMatching("P2", "E2"),
          ProjectMatching("P3", "E3")))

      // engineers order is important

      val engineersPreferences2: Map[String, Seq[String]] = Map(
        "E2" -> Seq("P1", "P2", "P3"),
        "E3" -> Seq("P1", "P2", "P3"),
        "E1" -> Seq("P1", "P2", "P3")
      )

      new BasicJobFair().matches(engineersPreferences2, projectsPreferences, projectsCapacities).toSet must be(
        Set(ProjectMatching("P1", "E2"),
          ProjectMatching("P2", "E3"),
          ProjectMatching("P3", "E1")))
    }

    "work with the example provided" in {

      // { "engineers_preferences": { "Ada": [ "Citrine" ], "Bertha": [ "BlueGold",
      //"Citrine", "Aquamarine" ], "Candice": [ "Aquamarine", "Citrine", "BlueGold" ],
      //"Daniela": [ "Citrine", "Aquamarine" ] },
      val engineersPreferences: Map[String, Seq[String]] = Map(
        "Ada" -> Seq("Citrine"),
        "Bertha" -> Seq("BlueGold", "Citrine", "Aquamarine"),
        "Candice" -> Seq("Aquamarine", "Citrine", "BlueGold"),
        "Daniela" -> Seq("Citrine", "Aquamarine")
      )
      // "projects_preferences": { "Aquamarine":
      //[ "Bertha", "Candice", "Daniela" ], "BlueGold": [ "Bertha", "Candice" ],
      //"Citrine": [ "Bertha", "Candice", "Ada", "Daniela" ] },
      val projectsPreferences: Map[String, Seq[String]] = Map(
        "Aquamarine" -> Seq("Bertha", "Candice", "Daniela"),
        "BlueGold" -> Seq("Bertha", "Candice"),
        "Citrine" -> Seq("Bertha", "Candice", "Ada", "Daniela")
      )
      // "projects_capacities": { "Aquamarine": 1, "BlueGold": 2, "Citrine": 2 } }
      val projectsCapacities: Map[String, Int] = Map(
        "Aquamarine" -> 1,
        "BlueGold" -> 2,
        "Citrine" -> 2
      )
      // { "matches": [ { "project": "Citrine", "engineer": "Ada" }, { "project":
      //"BlueGold", "engineer": "Bertha" }, { "project": "Aquamarine", "engineer":
      //"Candice" }, { "project": "Citrine", "engineer": "Daniela" } ] }
      new BasicJobFair().matches(engineersPreferences, projectsPreferences, projectsCapacities).toSet must be(
      Set(ProjectMatching("Citrine", "Ada"),
        ProjectMatching("BlueGold", "Bertha"),
        ProjectMatching("Aquamarine", "Candice"),
        ProjectMatching("Citrine", "Daniela"))
      )
    }

  }
}
