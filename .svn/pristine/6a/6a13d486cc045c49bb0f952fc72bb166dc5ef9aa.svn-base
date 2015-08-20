package com.hiynn.drools.engine;

import java.util.Collection;
import java.util.Iterator;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderError;
import org.drools.builder.KnowledgeBuilderErrors;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatefulKnowledgeSession;

import com.hiynn.drools.model.Comment;

public class Drools {

	
	public static void main(String[] args){
		KnowledgeBuilder kb = KnowledgeBuilderFactory.newKnowledgeBuilder();
		kb.add(ResourceFactory.newClassPathResource("tags.drl",Drools.class), ResourceType.DRL);
		if(kb.hasErrors()){
			KnowledgeBuilderErrors errors = kb.getErrors();
			for(Iterator<KnowledgeBuilderError> it = errors.iterator();it.hasNext();){
				System.out.println(it.next());
			}
		}
		Collection collection = kb.getKnowledgePackages();
		KnowledgeBase knowledgeBase = KnowledgeBaseFactory.newKnowledgeBase();
		knowledgeBase.addKnowledgePackages(collection);
		
		StatefulKnowledgeSession statefulSession = knowledgeBase.newStatefulKnowledgeSession();
		Comment comment = new Comment();
		comment.setContent("噪音");
		statefulSession.insert(comment);
		
		statefulSession.fireAllRules();
		statefulSession.dispose();
		System.out.println("comment.tag: "+comment.getTag());
		System.out.println("end ....");
	}
}
