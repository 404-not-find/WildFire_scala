package com.hiynn.drools.engine;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

public class Noise {

	/**
	 * 关键词库
	 * @return
	 */
	public static Set<String> getKeyWordsCollection(){
		Set<String> set = new LinkedHashSet<String>();
		InputStreamReader reader = null;
		try {
			reader = new InputStreamReader(NoiseAnalyze.class.getResourceAsStream("/noise.txt"));
			BufferedReader br = new BufferedReader(reader);
			String line = "";
			line = br.readLine();
			
			while(line != null){
				set.add(line);
				line = br.readLine();
			}
			br.close();
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		return set;
	}
	/**
	 * 根据索引直接得到值
	 * @param index
	 * @return
	 */
	public static String getKeyWord(int index){
		String keyWord = "";
		Set<String> set = getKeyWordsCollection();
		System.out.println(set);
		String[] strSet = new String[set.size()];
		set.toArray(strSet);
		if(index <strSet.length){
			keyWord = strSet[index];
		}
		return keyWord;
	}
	public static String getKeyWords(){
		String result = new String();
		Set<String> set = getKeyWordsCollection();
		if(!set.isEmpty()){
			result+="[";
			Iterator<String> it = set.iterator();
			while(it.hasNext()){
				result+=it.next();
				result+="|";
			}
			result = result.substring(0, result.length()-1);
			result+="]";
		}
		
		return result.toString();
	}
	
	public static void main(String[] args){
		String s = getKeyWord(3);
		
		System.out.println(s);
	}
}
