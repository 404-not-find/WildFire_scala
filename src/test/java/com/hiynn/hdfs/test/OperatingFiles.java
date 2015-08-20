package com.hiynn.hdfs.test;

import java.io.*;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;  
import org.apache.hadoop.fs.FileSystem;  
import org.apache.hadoop.fs.*;
import org.apache.hadoop.io.IOUtils;

public class OperatingFiles {
	
	//initialization  
    static Configuration conf = new Configuration();  
    static FileSystem hdfs;  
    static {   
    	String hdfs_uri = "hdfs://192.168.0.5:9000";
        try {  
            hdfs = FileSystem.get(URI.create(hdfs_uri), conf);  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }   
    //list all files  
    public void listFiles(String dirName) throws IOException {  
        Path f = new Path(dirName);  
        FileStatus[] status = hdfs.listStatus(f);  
        System.out.println(dirName + " has all files:");  
        for (int i = 0; i< status.length; i++) {  
            System.out.println(status[i].getPath().toString());  
        }  
    }  
    //create a direction  
    public void createDir(String dir) throws IOException {  
        Path path = new Path(dir);  
        hdfs.mkdirs(path);  
        System.out.println("new dir \t" + conf.get("fs.default.name") + dir);  
    }  
    //create a new file  每次覆盖之前的文件内容
    public void createFile(String fileName, String fileContent) throws IOException {  
        Path dst = new Path(fileName);  
        byte[] bytes = fileContent.getBytes();  
        FSDataOutputStream output = hdfs.create(dst);  
        output.write(bytes);  
        output.close();
        System.out.println("new file \t" + conf.get("fs.default.name") + fileName);  
    }
    //追加到存在的文件中  不覆盖之前的内容
   /*public void appendFileData(String fileName, String fileContent) throws IOException {  
    	Configuration conf = new Configuration();
    	FileSystem fs = null;
    	fs = FileSystem.get(URI.create(fileName), conf);
    	InputStream in = new ByteArrayInputStream(fileContent.getBytes());  
    	Path dst = new Path(fileName); 
    	OutputStream out = fs.append(dst);
    	IOUtils.copyBytes(in, out, 4096, true);*/
    	
    	
        //Path dst = new Path(fileName);  
        //FSDataInputStream append = hdfs.open(dst);  
        //InputStream in = new ByteArrayInputStream(fileContent.getBytes());
        //IOUtils.copyBytes(in, append, 4096, true);
        //IOUtils.closeStream(in);
        //BufferedReader br = new BufferedReader(new FileReader(fileContent));
        //String line = "";
        //while((line=br.readLine()) != null){
        	//append.writeBytes(fileContent);
        	//append.flush();
        //}
        //append.close();
        //br.close();
    	
    	/*FileOutputStream fos = new FileOutputStream(fileName);
    	OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
    	BufferedWriter out = new BufferedWriter(osw);
    	out.write(fileContent);
    	out.newLine();
    	out.flush();*/
    	
        //System.out.println("append file \t" + conf.get("fs.default.name") + fileName);  
    //}
    //judge a file existed? and delete it!  
    public void deleteFile(String fileName) throws IOException {  
        Path f = new Path(fileName);  
        boolean isExists = hdfs.exists(f);  
        if (isExists) { //if exists, delete  
            boolean isDel = hdfs.delete(f,true);  
            System.out.println(fileName + "  delete? \t" + isDel);  
        } else {  
            System.out.println(fileName + "  exist? \t" + isExists);  
        }  
    } 
    //判断文件是否存在  Hdfs   存在输出true  不存在输出false
    public boolean exitsFile(String fileName)throws IOException 
    {
    	Path f = new Path(fileName); 
        boolean isExists = hdfs.exists(f);
        System.out.println(fileName + " exists " +isExists);
        return isExists;
    }

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		OperatingFiles ofs = new OperatingFiles();
		String dir = "/testli2";
		//ofs.createDir(dir);
		//ofs.createFile(dir+"/hello.txt","hello world 123 ");
		//ofs.deleteFile("/testli2/hello.txt");
		//ofs.exitsFile("/testli2/hello.txt");
		ofs.listFiles("/");
	}

}
