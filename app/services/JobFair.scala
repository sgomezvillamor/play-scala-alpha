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
      s""" BasicJobFair
         | Engineer preferences: ${engineersPreferences}
         | Projects preferences: ${projectsPreferences}
         | Projects capacities: ${projectsCapacities}
         |""".stripMargin)

    var matches = collection.mutable.Map[String, Seq[String]]().withDefaultValue(Seq.empty[String])

    engineersPreferences.foreach { case (e, prefs) =>
      Logger.debug(s"engineer $e with prefs $prefs")
      val loop = new Breaks
      import loop.{breakable, break}
      breakable {
        prefs.foreach { pref =>
          Logger.debug(s"pref $pref: projectsPrefs(pref)= ${matches(pref)} and projectsCapacities(pref)=${projectsCapacities(pref)}")
          if (matches(pref).size < projectsCapacities(pref)
            && projectsPreferences(pref).contains(e)) {
            matches(pref) = (matches(pref) :+ e)
            Logger.debug(s"projectsPrefs(pref) append $e = ${matches(pref)}")
            break()
          }
        }
      }
    }

    Logger.info(s"BasicJobFair: matches = $matches")

    matches.flatMap { case (proj, prefs) =>
      prefs.map(ProjectMatching(proj, _))
    }.toSeq
  }
}
