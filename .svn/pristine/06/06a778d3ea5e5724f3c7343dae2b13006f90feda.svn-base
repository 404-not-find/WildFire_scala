package dao

import scala.collection.immutable.HashMap

//import scala.collection.JavaConversions.mapAsScalaMap


/**
* Created by yanghao on 5/4/15.
*/

object Neo4jDAOTest  {
  def main(args: Array[String]) = {
    neo4jTest
  }

  def neo4jTest() {
//    val data = new scala.collection.mutable.Map[String, AnyRef]
//    val data: scala.collection.mutable.Map[String, AnyRef] = new util.TreeMap[String, AnyRef]
      var data: Map[String, Any] = new HashMap[String, Any];
//      data = data + ("unknow" -> 12345);
//      var data: Map[String, AnyRef] = new HashMap[String, AnyRef]
      data += ("5" -> "qqqqqqqqqqqqqqqqqqq")
//      data -= "1"
//    data.put("2", "jia")
//    data.put("3", "guo")
//    data.put("4", "nian")


    val dao: TypeDAO = new TypeDAO()
    System.out.println(dao.createObjectData(data))
//    System.out.println(dao.deleteNodeById(5))
    System.out.println(dao.getDataById(4))
  }

}

