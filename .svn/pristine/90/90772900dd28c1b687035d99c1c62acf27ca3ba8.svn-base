package com.hiynn.drools.engine;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class NoiseAnalyze {

	/**
	 * 关键词库
	 * @return
	 */
	public static Set<String> getKeyWordsCollection(){
		Set<String> set = new HashSet<String>();
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
	 * 正面修饰词
	 * @return
	 */
			
	public static Set<String>  getPositiveQualifier(){
		Set<String> set = new HashSet<String>();
		set.add("小");
		
		return set;
	}
	
	/**
	 * 负面修饰词
	 * @return
	 */
			
	public static Set<String>  getNegativeQualifier(){
		Set<String> set = new HashSet<String>();
		set.add("大");
		
		return set;
	}
	
	/**
	 * 产品/性能/噪音 打标签，如果正面情绪总分>负面情绪总分，则为正面情绪；否则为负面情绪。
	 * @param str
	 * @return
	 */
	public static int tagNoise(String str){
		int point = 0;
		Set<String> keywordSet = getKeyWordsCollection();
		Set<String> positiveSet = getPositiveQualifier();
		Set<String> negativeSet = getNegativeQualifier();
		
		//先判断是否正面情绪
		if(keywordSet != null && !keywordSet.isEmpty()){
			Iterator<String> it = keywordSet.iterator();
			while(it.hasNext()){
				String word = it.next();
				int len = word.length();
				System.out.println("word: "+word);
				int index  = str.indexOf(word);
				if(index != -1 ){//如果找到了关键词,只考虑了找到第一次的情况
					for(int i=len;i<len+3;i++){
						String qualifierWords = str.substring(index+i,index+i+1);
						System.out.println("qualifierWords: "+qualifierWords);
						if(positiveSet.contains(qualifierWords)){
							point++;
							
						}
						if(negativeSet.contains(qualifierWords)){
							point--;
						}
					}
					
				}
			}
			
		}
		
		return point;
	}
	
	public static String printResult(int point){
		if(point >0){
			return "性能/噪音/正面";
		}else if(point == 0){
			return "性能/噪音/无此项";
		}else{
			return "性能/噪音/负面";
		}
	}
	public static void main(String [] args){
		String str = "外观大气，噪音小。空间大，用料足。";
		int result = tagNoise(str);
		System.out.println(result);
		System.out.println(printResult(result));
		
//		Set set = getKeyWordsCollection();
//		System.out.println(set);
		
	}
}
