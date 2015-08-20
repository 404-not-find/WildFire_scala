package com.hiynn.drools

import com.hiynn.drools.rule.DroolEngine
import com.hiynn.drools.model.Comment
import java.util.ArrayList

import dao.TypeDAO

import scala.collection.immutable.HashMap

object TestRule {


//   def main(args: Array[String]): Unit = {
//
//     val ksession  = DroolEngine.knowledgeSession("tags.drl");
//
////    	  val comment = new Comment();
//
//    	  var tag =  new ArrayList[String];
//
//    	  tag.add("使用一天，噪音很小，面积足够三口之家用了！很高大，总体还不错！")
//    	  ksession.insert(tag)
//    	  ksession.fireAllRules()
//    	  println(tag)
//          ksession.dispose()
//          println("读取tags.drl结束拉 ")
//   }

   def main (args: Array[String]) {
     neo4jTest()

  }

  def neo4jTest() {
    var data: Map[String, AnyRef] = new HashMap[String, AnyRef]
    data += ("1" -> "huha")
    //    data.put("2", "jia")
    //    data.put("3", "guo")
    //    data.put("4", "nian")
    val dao: TypeDAO = new TypeDAO
    System.out.println(dao.getDataById(0))
  }
}