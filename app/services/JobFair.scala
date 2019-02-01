package services

import java.util.Random

import javax.inject._
import play.api.Logger

case class ProjectMatching(project: String, engineer: String)

trait JobFair {
  def matches(engineersPreferences: Map[String, Seq[String]],
              projectsPreferences: Map[String, Seq[String]],
              projectsCapacities: Map[String, Int]): Seq[ProjectMatching]
}

@Singleton
class BasicJobFair extends JobFair {
  val rnd = new Random(System.currentTimeMillis())

  override def matches(engineersPreferences: Map[String, Seq[String]],
    projectsPreferences: Map[String, Seq[String]],
    projectsCapacities: Map[String, Int]): Seq[ProjectMatching] = {

    Logger.info(
      s""" JobFair
         | Engineer preferences: ${engineersPreferences}
         | Projects preferences: ${projectsPreferences}
         | Projects capacities: ${projectsCapacities}
         |""".stripMargin)

    // TODO: do logic

    Seq(
      ProjectMatching("proj1", s"engA ${rnd.nextInt()}"),
      ProjectMatching("proj2", s"engA ${rnd.nextInt()}")
    )
  }
}
