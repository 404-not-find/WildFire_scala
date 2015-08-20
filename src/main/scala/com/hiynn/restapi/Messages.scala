package com.hiynn.restapi

import java.util.Date

sealed trait ResultMessage

case class Concept(id: Long, pid: Long, name: String)
case class Attribute(id: Long, dataType: String, name: String)

case class Created(location: String) extends ResultMessage
case class Success(message: String) extends ResultMessage
case class GetItem(item: AnyRef) extends ResultMessage
case class GetAllItems[T](items: List[T]) extends ResultMessage

case class Error(message: String)

sealed trait RequestMessage

case class GetById(typeName: String, id: Long) extends RequestMessage
case class DeleteById(typeName: String, id: Long) extends RequestMessage
case class Update[T](item: T) extends RequestMessage
case class Create[T](item: T) extends RequestMessage
case class AllItems(typeName: String) extends RequestMessage