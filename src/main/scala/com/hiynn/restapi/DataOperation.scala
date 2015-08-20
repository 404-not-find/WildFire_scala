package com.hiynn.restapi

import akka.actor.Actor
import akka.actor.actorRef2Scala
import akka.event.Logging
import scala.collection.immutable.Map
import scala.collection.immutable.HashMap

trait DataOperations {

  def getConceptById(id: Long) = {
    GetItem(new Concept(id, 0, "item1"))
  }

  def allConcepts() = {
    try {
      GetAllItems[Map[String, Any]](List(new HashMap[String, Any]()))
    } catch {
      case e: Exception => {
        println(e.getMessage())
        List()
      }
    }
  }

  def delete(id: Long) = {
    Success("deleted successfully")
  }

  def createConcept(item: Concept) = {
    Created("")
  }

  def updateConcept(concept: Concept) = {
    getConceptById(concept.id)
  }
}

class DataActor extends Actor with DataOperations {
  val log = Logging(context.system, this)
  def receive = {
    case GetById(typeName, id) => sender ! getConceptById(id)
    case Update(item) => if (item.isInstanceOf[Concept]) { sender ! updateConcept(item.asInstanceOf[Concept]) }
    case DeleteById(typeName, id) => sender ! delete(id)
    case Create(item) => if (item.isInstanceOf[Concept]) { sender ! createConcept(item.asInstanceOf[Concept]) }
    case AllItems(typeName) => sender ! allConcepts()
  }
}
