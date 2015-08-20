package com.hiynn.restapi

import akka.actor.Actor
import akka.actor.actorRef2Scala
import akka.event.Logging

trait KnowledgeOperations {

  def getConceptById(id: Long) = {
    GetItem(new Concept(id, 0, "item1"))
  }

  def allConcepts() = {
    try {
      GetAllItems[Concept](List(new Concept(100, 0, "item1")))
    } catch {
      case e: Exception => {
        println(e.getMessage())
        List()
      }
    }
  }

  def deleteConceptById(id: Long) = {
    Success("deleted successfully")
  }

  def createConcept(item: Concept) = {
    Created("Concept")
  }

  def updateConcept(concept: Concept) = {
    getConceptById(concept.id)
  }

  def getAttributeById(id: Long) = {
    GetItem(new Attribute(id, "int", "item1"))
  }

  def allAttributes() = {
    try {
      GetAllItems[Attribute](List(new Attribute(100, "int", "item1")))
    } catch {
      case e: Exception => {
        println(e.getMessage())
        List()
      }
    }
  }

  def deleteAttributeById(id: Long) = {
    Success("deleted successfully")
  }

  def createAttribute(item: Attribute) = {
    Created("Attribute")
  }

  def updateAttribute(concept: Attribute) = {
    getAttributeById(concept.id)
  }
}

class KnowledgeActor extends Actor with KnowledgeOperations {
  val log = Logging(context.system, this)
  def receive = {
    case GetById(typeName, id) => typeName match {
      case "Concept" => sender ! getConceptById(id)
      case "Attribute" => sender ! getConceptById(id)
    }
    case Update(item) => if (item.isInstanceOf[Concept]) { sender ! updateConcept(item.asInstanceOf[Concept]) }
    case DeleteById(typeName, id) => typeName match {
      case "Concept" => sender ! deleteConceptById(id)
      case "Attribute" => sender ! deleteConceptById(id)
    }
    case Create(item) => if (item.isInstanceOf[Concept]) { sender ! createConcept(item.asInstanceOf[Concept]) }
    case AllItems(typeName) => typeName match {
      case "Concept" => sender ! allConcepts()
      case "Attribute" => sender ! allConcepts()
    }
  }
}
