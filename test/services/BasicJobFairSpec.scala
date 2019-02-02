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
      new BasicJobFair().matches(engineersPreferences, projectsPreferences, projectsCapacities) must be(
        Seq(ProjectMatching("P1", "E1"),
          ProjectMatching("P3", "E3"),
          ProjectMatching("P2", "E2")))
    }
  }
}
