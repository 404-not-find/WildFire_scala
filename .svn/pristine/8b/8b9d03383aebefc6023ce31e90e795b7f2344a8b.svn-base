package com.hiynn.restapi

import scala.concurrent.duration.DurationInt

import org.json4s.DefaultFormats

import com.hiynn.restapi.HyRequest.WithProps

import akka.actor.Actor
import akka.actor.ActorRef
import akka.actor.OneForOneStrategy
import akka.actor.Props
import akka.actor.ReceiveTimeout
import akka.actor.SupervisorStrategy.Stop
import akka.actor.actorRef2Scala
import spray.http.StatusCode
import spray.http.StatusCodes.BadRequest
import spray.http.StatusCodes.GatewayTimeout
import spray.http.StatusCodes.InternalServerError
import spray.http.StatusCodes.OK
import spray.httpx.Json4sSupport
import spray.routing.RequestContext

trait HyRequest extends Actor with Json4sSupport {
  def requestContext: RequestContext

  def target: ActorRef

  def message: RequestMessage

  import context._

  //  implicit def json4sFormat = DefaultFormats

  setReceiveTimeout(2.seconds)

  target ! message

  def receive = {
    case Created(location) => complete(spray.http.StatusCodes.Created, location)
    case GetItem(item) => complete(OK, item)
    case GetAllItems(list) => complete(OK, list)
    case Success(message) => complete(OK, message)
    case Error(message) => complete(BadRequest, message)
    case ReceiveTimeout => complete(GatewayTimeout, "Request Timeout")
  }

  def complete[T <: AnyRef](status: StatusCode, obj: T) = {
    requestContext.complete(status, obj)
    stop(self)
  }

  override val supervisorStrategy =
    OneForOneStrategy() {
      case e => {
        complete(InternalServerError, Error(e.getMessage))
        Stop
      }
    }
}

object HyRequest {

  case class WithProps(requestContext: RequestContext, props: Props, message: RequestMessage) extends HyRequest {
    lazy val target = context.actorOf(props)

    implicit def json4sFormats = DefaultFormats
  }

}

trait HyRequestCreator {
  this: Actor =>

  def hyRequest(requestContext: RequestContext, props: Props, message: RequestMessage) =
    context.actorOf(Props(new WithProps(requestContext, props, message)))
}
