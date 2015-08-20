package com.hiynn.restapi

import scala.collection.immutable.HashMap
import scala.collection.immutable.Map
import org.json4s.DefaultFormats
import akka.actor.Actor
import akka.actor.Actor
import akka.actor.ActorLogging
import akka.actor.ActorRef
import akka.actor.Props
import spray.http.MediaTypes._
import spray.httpx.Json4sSupport
import spray.routing.Directive.pimpApply
import spray.routing.HttpService
import spray.routing.Route
import dao.TypeDAO

class DispatchServiceActor extends Actor with DispatchService with HyRequestCreator {
  implicit def actorRefFactory = context

  def receive = runRoute(kRoute ~ staticRoute)

  def handleRequest(domainName: String, message: RequestMessage): Route =
    ctx =>
      domainName match {
        case "knowledge" =>
          hyRequest(ctx, Props[KnowledgeActor], message)
        case "data" =>
          hyRequest(ctx, Props[DataActor], message)
      }
}

trait DispatchService extends HttpService with Json4sSupport {
  val json4sFormats = DefaultFormats

  val kRoute =
    path("knowledge" / "Concept" / LongNumber) {
      id: Long =>
        get {
          rejectEmptyResponse {
            handleRequest("knowledge",
              GetById("Concept", id))
          }
        } ~ put {
          entity(as[Concept]) {
            cc =>
              handleRequest("knowledge",
                Update[Concept](new Concept(id, cc.pid, cc.name)))
          }
        } ~ delete {
          handleRequest("knowledge",
            DeleteById("Concept", id))
        }
    } ~ path("knowledge" / "Concept") {
      get {
        handleRequest("knowledge",
          AllItems("Concept"))
      } ~ post {
        entity(as[Map[String,String]]) {
          m =>
            implicit val executor = actorRefFactory.dispatcher
            var javaMap = new java.util.HashMap[String,Object];
            for((k,v) <- m){
              javaMap.put(k, v);
            }
            println("run here!999")
            println(javaMap)
            var dao = new TypeDAO();
            dao.createObjectData(javaMap);
            complete("保存成功!")
        }
      }
    } ~ post {
      complete("successss hahhah!")
    } ~ path("knowledge" / "Attribute" / LongNumber) {
      id: Long =>
        get {
          rejectEmptyResponse {
            handleRequest("knowledge",
              GetById("Attribute", id))
          }
        } ~ put {
          entity(as[Attribute]) {
            cc =>
              handleRequest("knowledge",
                Update[Attribute](new Attribute(id, cc.dataType, cc.name)))
          }
        } ~ delete {
          handleRequest("knowledge",
            DeleteById("Attribute", id))
        }
    } ~ path("knowledge" / "Attribute") {
      get {
        handleRequest("knowledge",
          AllItems("Attribute"))
      }
    } ~ post {
      entity(as[Attribute]) {
        cc =>
          handleRequest("knowledge",
            Create[Attribute](new Attribute(0, cc.dataType, cc.name)))
      }
    } 
/*
 * 所有静态页面都放到resources/web 下，静态路由
 */
  val staticRoute = {
    path("") {
      getFromResource("web/index.html")
    }~ path("ontology"){
      getFromResource("web/ontology.html")
    } ~ path("bagWords"){
      getFromResource("web/bagWords.html")
    } ~ {
      getFromResourceDirectory("web")
    }
    
  }

  val dRoute =
    path("data" / LongNumber) {
      id: Long =>
        get {
          rejectEmptyResponse {
            handleRequest("data",
              GetById("data", id))
          }
        } ~ put {
          entity(as[Map[String, Any]]) {
            cc =>
              handleRequest("data",
                Update[Map[String, Any]](new HashMap[String, Any]()))
          }
        } ~ delete {
          handleRequest("data",
            DeleteById("data", id))
        }
    } ~ path("data") {
      get {
        handleRequest("data",
          AllItems("data"))
      }
    } ~ post {
      entity(as[Map[String, Any]]) {
        cc =>
          handleRequest("data",
            Create[Map[String, Any]](new HashMap[String, Any]))
      }
    }

  def handleRequest(domainName: String, message: RequestMessage): Route
}

