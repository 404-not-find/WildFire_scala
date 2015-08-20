package com.hiynn.drools.engine;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Stack;

public class EmotionAnalyze {


	
	/**
	 * 正面情绪词库，去掉表示程度的形容词
	 * @return
	 */
	public static Set<String> getPositiveEmotionCollection(){
		Set<String> set = new HashSet<String>();
		set.add("好");
		set.add("满意");
		set.add("喜欢");
		set.add("棒");
		set.add("给力");
		set.add("满分");
		set.add("物美价廉");
		set.add("可以");
		set.add("支持");
		set.add("实用");
		set.add("理想");
		set.add("爽");
		set.add("推荐");
		set.add("实惠");
		set.add("正");
		set.add("信赖");
		set.add("负责");
		set.add("赞");
		set.add("相信");
		set.add("帅");
		
		
		return set;
		
	}
	
	/**
	 * 否定词词库，跟正面情绪库和负面情绪库结合后，才可以正确探知用户的情绪。
	 * TODO:只考虑了一重否定
	 * @return
	 */
	public static Set<String> getNegSentence(){
		Set<String> set = new HashSet<String>();
		set.add("没");
		set.add("不");
		set.add("无");
		set.add("莫");
//		set.add("非");//口语中常和非常结合，是个副词
		
		return set;
	}
	
	/**
	 * 负面情绪库
	 * @return
	 */
	public static Set<String> getNegativeEmotionCollection(){
		Set<String> set = new HashSet<String>();
		set.add("差");
		set.add("慢");
		set.add("一般");
		set.add("失望");
		set.add("错");
		set.add("伤心");
		set.add("坏");
		set.add("郁闷");
		set.add("遗憾");
		set.add("坏");
		set.add("垃圾");
		set.add("坑爹");
		
		
		return set;
		
	}
	/**
	 * 情感分析，如果正面情绪总分>负面情绪总分，则为正面情绪；否则为负面情绪。
	 * @param str
	 * @return
	 */
	public static int emotionAnalyze(String str){
		
		int result=0,negPoint=0,positivePoint = 0;
		Set<String> positiveSet = getPositiveEmotionCollection();
		Set<String> negSet = getNegSentence();
		Set<String> negativegSet = getNegativeEmotionCollection();
		//先判断是否正面情绪
		if(positiveSet != null && !positiveSet.isEmpty()){
			Iterator<String> it = positiveSet.iterator();
			while(it.hasNext()){
				String word = it.next();
				System.out.println("word: "+word);
				int index  = str.indexOf(word);
				if(index != -1 ){//如果找到了正面情绪词,只考虑了找到第一次的情况
					positivePoint++;
					System.out.println("遇到正面情绪词positivePoint: "+positivePoint);
					if(index == 0){//正面情绪+1
						System.out.println("positivePoint: "+positivePoint);
					}else{
							for(int step=0;index>0 && step<6;index--,step++){
								String negWords = str.substring(index-1,index);
								System.out.println("判断正面情绪negWords: "+negWords);
								if(negSet.contains(negWords)){//如果包含否定词
									negPoint++;
									positivePoint--;
									System.out.println("判断正面情绪negPoint: "+negPoint);
									System.out.println("判断正面情绪positivePoint: "+positivePoint);
								}
							}
				
					}
				}
			}
		}
		//判断是否负面情绪
		if(negativegSet != null && !negativegSet.isEmpty()){
			Iterator<String> it = negativegSet.iterator();
			while(it.hasNext()){
				String word = it.next();
				System.out.println("word: "+word);
				int index  = str.indexOf(word);
				if(index != -1 ){//如果找到了负面情绪词,只考虑了找到第一次的情况
					negPoint++;
					System.out.println("negPoint: "+negPoint);
					if(index == 0){//负面情绪+1
						System.out.println("negPoint: "+negPoint);
					}else{
							for(int step=0;index>0 && step<6;index--,step++){
								String negWords = str.substring(index-1,index);
								System.out.println("negWords: "+negWords);
								if(negSet.contains(negWords)){//如果包含否定词
									negPoint--;
									positivePoint++;
									System.out.println("遇到否定词,negPoint: "+negPoint);
									System.out.println("遇到否定词,positivePoint: "+positivePoint);
								}
							}
				
					}
				}
			}
		}
		
		result = positivePoint - negPoint;
		
		return result;
	}
	
	public static String emotionCN(int point){
		if(point >0){
			return "正面";
		}else{
			return "负面";
		}
	}
	public static void main(String [] args){
		String str = "真是火箭速度，刚拍就送货了，海尔25服务不错，质量还不知道，用了才清楚！但送货及时服务也不错，点个赞！！";
		int result = emotionAnalyze(str);
		System.out.println(result);
		System.out.println(emotionCN(result));
	}
	
	
}
