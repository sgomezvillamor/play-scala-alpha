package controllers

import javax.inject._
import play.api.mvc._
import play.api.libs.json._
import play.api.libs.functional.syntax._

import scala.util.{Failure, Success, Try}

case class JobFairPayload(engineersPreferences: Map[String, Seq[String]],
                          projectsPreferences: Map[String, Seq[String]],
                          projectsCapacities: Map[String, Int])


case class ProjectMatching(project: String, engineer: String)

case class JobFairResult(matches: Seq[ProjectMatching])

/**
  * This controller creates an `Action` to handle HTTP requests to the
  * JobFair application.
  */
@Singleton
class JobFairController @Inject() extends Controller {

  def jobfair = Action { implicit request =>
    val jobFairPayload = Json.fromJson[JobFairPayload](request.body.asJson.get).get

    println(jobFairPayload)

    Try {
      JobFairResult(Seq(
        ProjectMatching("proj1", "engA"),
        ProjectMatching("proj2", "engX")
      ))
    } match {
      case Success(res) => Ok(Json.toJson(res))
      case Failure(ex) => InternalServerError(Json.toJson(
        Map("status" -> "KO",
          "message" -> ex.getMessage,
          "stacktrace" -> ex.getStackTrace.mkString("\n"))
      ))
    }

  }

  implicit val projectMatchingWrites = new Writes[ProjectMatching] {
    def writes(obj: ProjectMatching) = Json.obj(
      "project" -> obj.project,
      "engineer" -> obj.engineer
    )
  }

  implicit val jobFairResultWrites = new Writes[JobFairResult] {
    def writes(obj: JobFairResult) = Json.obj(
      "matches" -> obj.matches
    )
  }

  implicit val jobFairPayloadReads: Reads[JobFairPayload] = (
    (__ \ "engineers_preferences").read[Map[String, Seq[String]]] and
      (__ \ "projects_preferences").read[Map[String, Seq[String]]] and
      (__ \ "projects_capacities").read[Map[String, Int]]
    ) (JobFairPayload.apply _)

}



