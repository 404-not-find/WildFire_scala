package com.hiynn.drools.rule

import org.kie.api.io.ResourceType
import org.kie.internal.KnowledgeBaseFactory
import org.kie.internal.builder.KnowledgeBuilderFactory
import org.kie.internal.io.ResourceFactory
import org.kie.internal.runtime.StatefulKnowledgeSession
import com.hiynn.drools.model.Comment
import com.hiynn.hbase.HbaseTool
import java.util.ArrayList
import scala.collection.mutable.HashMap

object DroolEngine extends App {


  def knowledgeSession(fileName: String) : StatefulKnowledgeSession = {
    val kbuilder  = KnowledgeBuilderFactory.newKnowledgeBuilder()
    kbuilder.add(ResourceFactory.newClassPathResource(fileName), ResourceType.DRL)
    if(kbuilder.hasErrors()){
			val errors = kbuilder.getErrors();
			val array =  java.util.Arrays.asList(errors).toArray();
			array.foreach(println)
		
		}
    val kbase = KnowledgeBaseFactory.newKnowledgeBase()
    kbase.addKnowledgePackages(kbuilder.getKnowledgePackages())
    kbase.newStatefulKnowledgeSession()
  }
  
  
def _processComment():Unit = {
  val ksession = knowledgeSession("tags.drl")
  var hbase = new HbaseTool();
  var keyvalue = hbase.queryAll("JDcomments")
 
     for(i <- 0 to keyvalue.size()-1){
    	 val comment = new Comment();
    	 var tag =  new ArrayList[String];
        for((k,v) <- keyvalue.get(i)){
           if(k == "key"){
              comment.setId(v)
           }
          if(k == "value"&&v != "" ){
             comment.setContent(v)
             comment.setTag(tag);
             System.out.println(v)
			 ksession.insert(comment)
		     ksession.fireAllRules()
		     println(comment.getTag())
		     if(!comment.getTag().isEmpty()){
		      var it =  comment.getTag().iterator();
		      while(it.hasNext()){
		        var t = it.next();
		        println("sssssssss: "+t)
		        var tagName = t.toString.split(":")(0);
		        println("tagName: "+tagName)
		        var tagValue = t.toString.split(":")(1);
		        println("tagValue: "+tagValue)
		        println(comment.getId())
//		         hbase.insertData("JDcomments",comment.getId(), "comment_info", tagName, tagValue)
		        
		      }
		     }
          }
          
        }
     }
    ksession.dispose()
  println("读取tags.drl结束")
  
}
 
def processComment():Unit = {
  val ksession = knowledgeSession("tags.drl")
  var hbase = new HbaseTool();
  var keyvalue = hbase.queryAll("JDcomments")
 
     for(i <- 0 to keyvalue.size()-1){
    	 var content =  new ArrayList[String];
    	 var id= new String("");
        for((k,v) <- keyvalue.get(i)){
           if(k == "key"){
             id = v;
           }
          if(k == "value"&&v != "" ){
        	 content.add(v)
			 ksession.insert(content)
//			 println("before rule:"+content)
		     ksession.fireAllRules()
//		     println("运行完规则:"+content)
//		     println("content.size(): "+content.size())
		     if(!content.isEmpty() && content.size()>1){
		       var it = (content.subList(1, content.size())).iterator;
		       while(it.hasNext){
		        var t = it.next();
		        var tagName = t.toString.split(":")(0);
		        println("tagName: "+tagName)
		        var tagValue = t.toString.split(":")(1);
		        println("tagValue: "+tagValue)
		        println("id: "+id)
//		         hbase.insertData("JDcomments",id, "comment_info", tagName, tagValue)
		        
		      }
		     }
          }
          
        }
     }
    ksession.dispose()
    println("读取tags.drl结束")
  
}
 
   processComment(); 
    
}