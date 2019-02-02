package services

import java.util.Random

import javax.inject._
import play.api.Logger

import scala.util.control.Breaks

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

    var projectsPrefs = collection.mutable.Map[String, Seq[String]]().withDefaultValue(Seq.empty[String])

    engineersPreferences.foreach { case (e, prefs) =>
      println(s"engineer $e with prefs $prefs")
      val loop = new Breaks
      import loop.{breakable, break}
      breakable {
        prefs.foreach { pref =>
          println(s"pref $pref: projectsPrefs(pref)= ${projectsPrefs(pref)} and projectsCapacities(pref)=${projectsCapacities(pref)}")
          if (projectsPrefs(pref).size < projectsCapacities(pref)
            && projectsPreferences(pref).contains(e)) {
            projectsPrefs(pref) = (projectsPrefs(pref) :+ e)
            println(s"projectsPrefs(pref) append e= ${projectsPrefs(pref)}")
            break()
          }
        }
      }
    }

    println(projectsPrefs)

    projectsPrefs.flatMap { case (proj, prefs) =>
      prefs.map(ProjectMatching(proj, _))
    }.toSeq
  }
}
