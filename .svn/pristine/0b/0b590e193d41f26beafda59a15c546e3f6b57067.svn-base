

package com.hiynn.drools

import com.hiynn.hbase.HbaseTool

object Test {

  def main(args: Array[String]): Unit = {
    val hbase = new HbaseTool
     /*var array =  hbase.getAllTables
     for(a <- array){
       println("------------>"+a)
     }*/
 //    var keyvalue = hbase.insertData("JDcomments","415584793", "comment_info", "comment_tag", "哈喽")
     var keyvalue1 = hbase.QueryByrowkey("JDcomments", "ffbecf18-5b10-47a1-a99c-d9154c06689f")
     for(i <- 0 to keyvalue1.size()-1){
        for((k,v) <- keyvalue1.get(i)){
          println(k+"------->"+v)
        }
     }
   /* val digits = Set(4,2)
     digits + 5
    println(digits)*/
  }

}