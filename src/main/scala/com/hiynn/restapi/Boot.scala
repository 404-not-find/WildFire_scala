package com.hiynn.restapi

import akka.actor.ActorSystem
import akka.actor.Props
import akka.actor.actorRef2Scala
import akka.io.IO
import spray.can.Http

object Boot extends App {
  implicit val system = ActorSystem("hydata-analysis")
  val service = system.actorOf(Props[DispatchServiceActor], "hydata-service")
  IO(Http) ! Http.Bind(service, interface = "localhost", port = 8080)

}
