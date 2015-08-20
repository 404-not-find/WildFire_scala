package com.hiynn.hbase

import org.apache.hadoop.hbase.HBaseConfiguration
import org.apache.hadoop.hbase.HColumnDescriptor
import org.apache.hadoop.hbase.HTableDescriptor
import org.apache.hadoop.hbase.client.Delete
import org.apache.hadoop.hbase.client.HBaseAdmin
import org.apache.hadoop.hbase.client.HTablePool
import org.apache.hadoop.hbase.client.Put
import org.apache.hadoop.hbase.client.Scan
import org.apache.hadoop.hbase.client.Get
import org.apache.hadoop.hbase.filter.SingleColumnValueFilter
import org.apache.hadoop.hbase.filter.CompareFilter.CompareOp
import org.apache.hadoop.hbase.util.Bytes
import java.util.ArrayList
import org.apache.hadoop.hbase.filter.FilterList
import org.apache.hadoop.hbase.filter.Filter
import scala.collection.mutable.HashMap
import scala.collection.mutable.LinkedList
import scala.collection.mutable.LinkedHashSet
import scala.collection.mutable.ArrayBuffer
/**
 * 过滤器：http://blog.csdn.net/saint1126/article/details/8257941
 * 比较器：BinaryComparator  按字节索引顺序比较指定字节数组，采用Bytes.compareTo(byte[])
        BinaryPrefixComparator 跟前面相同，只是比较左端的数据是否相同
		NullComparator 判断给定的是否为空
		BitComparator 按位比较 a BitwiseOp class 做异或，与，并操作
		RegexStringComparator 提供一个正则的比较器，仅支持 EQUAL 和非EQUAL
		SubstringComparator 判断提供的子串是否出现在table的value中  
 * 
 * 比较过滤器：
 *     行过滤：public RowFilter(org.apache.hadoop.hbase.filter.CompareFilter.CompareOp rowCompareOp, org.apache.hadoop.hbase.filter.WritableByteArrayComparable rowComparator) {} 
 *     列簇过滤：   public FamilyFilter(CompareOp familyCompareOp, WritableByteArrayComparable familyComparator)
 *     列名过滤：       QualifierFilter(CompareOp qualifierCompareOp, WritableByteArrayComparable qualifierComparator) 
 *     值过滤：    public ValueFilter(CompareOp valueCompareOp, WritableByteArrayComparable valueComparator)
 *     选定列簇和某一列，然后与列的value相比，正确的返回全部的row，注意如果某一行不含有该列，同样返回，除非通过filterIfColumnMissing 设置成真。
 *           SingleColumnValueFilter(byte[] family, byte[] qualifier, CompareOp compareOp, WritableByteArrayComparable comparator)
 *     SingleColumnValueExcludeFilter与 SingleColumnValueFilter相反
                 所有的row的实例匹配prefix的时候返回结果集合    PrefixFilter
 */
class HbaseTool {
     private val configuration =  HBaseConfiguration.create();
     configuration set("hbase.zookeeper.property.clientPort", "2181");  
     configuration set("hbase.zookeeper.quorum", "192.168.0.6");  
     
     /**
      * 获取所有表以及表字段
      * 
      */
     def getAllTables(): HashMap[String,ArrayBuffer[String]] = {
          val map = new HashMap[String,ArrayBuffer[String]]
          var hBaseadmin =  new HBaseAdmin(configuration)
          var tables = hBaseadmin.listTables()
          for(table <- tables){
             var array = ArrayBuffer[String]()
             var families = table.getFamiliesKeys()
             var iterator = families.iterator()
             while(iterator.hasNext())
               array += new String(iterator.next())
             map(table.getNameAsString())=array
          }
          map
     }
     /**
      * 创建table
      * @param tablename
      * @param familys
      */
     def createTable(tablename:String,familys:Array[String]){
        var hBaseadmin =  new HBaseAdmin(configuration)
        if(hBaseadmin.tableExists(tablename)){
           hBaseadmin.disableTable(tablename)
           hBaseadmin.deleteTable(tablename)
        }
        var tableDescriptor = new HTableDescriptor(tablename)           //表示table的schame
        for(column <- familys){
          tableDescriptor.addFamily(new HColumnDescriptor(column))    //HColumnDescriptor实例可以对列族属性的设置
        }            
        hBaseadmin.createTable(tableDescriptor);  
     }
     /**
      * 插入数据 
      * @param tableName 
      * @param key 
      */
     def insertData(tableName:String,key:String,family:String,qualifier:String,value:String){
        var pool = new HTablePool(configuration, 1000)
        var table = pool.getTable(tableName)
        var put = new Put(key.getBytes())                   //每个put代表一行  也就是一行记录
        put.add(family.getBytes(), qualifier.getBytes(), value.getBytes("utf-8"))// 本行数据的第一列  
        table.put(put)
        table.close()
     }
     /**
      * 删除一张表 
      * @param tableName 
      */
     def droptable(tableName:String){
        var hBaseadmin =  new HBaseAdmin(configuration)
        if(hBaseadmin.tableExists(tableName)){
           hBaseadmin.disableTable(tableName)
           hBaseadmin.deleteTable(tableName)
        }
     }
     /**
     * 根据 rowkey删除一条记录 
     * @param tableName 
     * @param rowkey 
     */
     def deleteRow(tableName:String,rowkey:String){
        var pool = new HTablePool(configuration, 1000)
        var table = pool.getTable(tableName)
        var del = new Delete(rowkey.getBytes())
        table.delete(del)
        table.close()
     }
     /**
      * 查询所有数据
      * @param tableName
      */
     def queryAll(tableName:String): ArrayList[HashMap[String,String]] = {
        var list = new ArrayList[HashMap[String,String]]
        var pool = new HTablePool(configuration, 1000)
        var table = pool.getTable(tableName)
        println(tableName)
        var scan = new Scan()
        scan.setBatch(100)
       // scan.addFamily("comment_info".getBytes())
        scan.addColumn("comment_info".getBytes(), "comment".getBytes())
        var rs = table.getScanner(scan)
        var res = rs.iterator()
        while(res.hasNext()){   //遍历表中的每条记录
           var row1 = res.next()
        //   println(new String(row1.getRow()))
           var array = row1.raw()
           for(elem <- array){              //遍历记录的所有的属性
              var row = new HashMap[String,String]
              row("key") = new String(elem.getRow())
            //  row("family") = new String(elem.getFamily())
              row("qualifier") = new String(elem.getQualifier())
              row("timestamp") = elem.getTimestamp().toString
              row("value") = new String(elem.getValue(),"UTF-8").replaceAll(" ", "")
              list.add(row)
            //  println(new String(elem.getRow())+"------->"+new String(elem.getFamily())+"------->"+new String(elem.getValue())+"---------->"+elem.getTimestamp())
           }
        }
        table.close()
       list
     }
     /**
      * 单条记录查询
      * @parameter tableName
      * @parameter rowkey
      */
     def QueryByrowkey(tableName:String,rowkey:String): ArrayList[HashMap[String,String]] = {
        var list = new ArrayList[HashMap[String,String]]
        var pool = new HTablePool(configuration, 1000)
        var table = pool.getTable(tableName)
        var scan = new Get(rowkey.getBytes())
        //scan.addFamily("column1".getBytes())     //限制列
        var rs = table.get(scan)
        var array = rs.raw()
           for(elem <- array){              //遍历记录的所有的属性
              var row = new HashMap[String,String]
              row("key") = new String(elem.getRow())
              row("family") = new String(elem.getFamily())
              row("qualifier") = new String(elem.getQualifier())
              row("timestamp") = elem.getTimestamp().toString
              row("value") = new String(elem.getValue(),"UTF-8").replaceAll(" ", "")
              list.add(row)
             /* println(new String(elem.getRow())+"------->"+new String(elem.getFamily())+"------->"+new String(elem.getQualifier())
              +"------->"+elem.getTimestamp()+"------->"+new String(elem.getValue()))*/
           }
       table.close()
       list
     }
     /** 
     * 单条件按查询，查询多条记录 
     * @param tableName 
     * @return ArrayList[HashMap[String,String]]
     */  
     def Querybyone(tableName:String):ArrayList[HashMap[String,String]] = {
        var list = new ArrayList[HashMap[String,String]]
        var pool = new HTablePool(configuration, 1000)
        var table = pool.getTable(tableName)
        var filter = new SingleColumnValueFilter(Bytes                                // 当列column1的值为aaa时进行查询  
                    .toBytes("commodity_info"), null, CompareOp.EQUAL, Bytes  
                    .toBytes("aaa"))
        var scan = new Scan() 
        scan.setFilter(filter)
        var rs = table.getScanner(scan)
        var res = rs.iterator()
        while(res.hasNext()){   //遍历表中的每条记录
           var row = res.next()
           println(new String(row.getRow()))
           var array = row.raw()
           for(elem <- array){              //遍历记录的所有的属性
              var row = new HashMap[String,String]
              row("key") = new String(elem.getRow())
              row("family") = new String(elem.getFamily())
              row("qualifier") = new String(elem.getQualifier())
              row("timestamp") = elem.getTimestamp().toString
              row("value") = new String(elem.getValue())
              list.add(row)
            /* println(new String(elem.getFamily())+"------->"+new String(elem.getQualifier())
              +"------->"+elem.getTimestamp()+"------->"+new String(elem.getValue()))*/
           }
        }
        table.close()
        list
     }
     /** 
     * 组合条件查询 
     * @param tableName
     * @return ArrayList[HashMap[String,String]] 
     */  
     def Querybymore(tableName:String):ArrayList[HashMap[String,String]] = {
        var list = new ArrayList[HashMap[String,String]]
        var pool = new HTablePool(configuration, 1000)
        var table = pool.getTable(tableName)
        /*var filters = new ArrayList[Filter]();  
            var filter1 = new SingleColumnValueFilter(Bytes  
                    .toBytes("column1"), null, CompareOp.EQUAL, Bytes  
                    .toBytes("aaa"));  
            filters.add(filter1);  
  
            var filter2 = new SingleColumnValueFilter(Bytes  
                    .toBytes("column2"), null, CompareOp.EQUAL, Bytes  
                    .toBytes("bbb"));  
            filters.add(filter2);  
  
            var filter3 = new SingleColumnValueFilter(Bytes  
                    .toBytes("column3"), null, CompareOp.EQUAL, Bytes  
                    .toBytes("ccc"));  
            filters.add(filter3);  
  
            var filterList1 = new FilterList(filters);*/
        var scan = new Scan() 
      //  scan.setFilter(filterList1)
        var rs = table.getScanner(scan)
        var res = rs.iterator()
        while(res.hasNext()){   //遍历表中的每条记录
           var row = res.next()
           println(new String(row.getRow()))
           var array = row.raw()
           for(elem <- array){              //遍历记录的所有的属性
              var row = new HashMap[String,String]
              row("key") = new String(elem.getRow())
              row("family") = new String(elem.getFamily())
              row("qualifier") = new String(elem.getQualifier())
              row("timestamp") = elem.getTimestamp().toString
              row("value") = new String(elem.getValue())
              list.add(row)
             /* println(new String(elem.getFamily())+"------->"+new String(elem.getQualifier())
              +"------->"+elem.getTimestamp()+"------->"+new String(elem.getValue()))*/
           }
        }
        table.close()
        list
     }
     
}