package edu.upf.taln.textdigester.summarizer.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.upf.taln.textdigester.model.TDDocument;
import edu.upf.taln.textdigester.resource.gate.GtUtils;
import gate.Annotation;

public class SummaryUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(SummaryUtil.class);
	
	public static Map<Annotation, Double> getSummary(Map<Annotation, Double> orderedSentences_SemScore, TDDocument doc, double percentageOfOriginalDocument) {
		Map<Annotation, Double> retMap = new HashMap<Annotation, Double>();
		
		if(orderedSentences_SemScore == null || orderedSentences_SemScore.size() == 0 || doc == null) {
			return null;
		}
		
		if(percentageOfOriginalDocument < 0 && percentageOfOriginalDocument > 100) {
			logger.warn("Length percentage of summary wrongly defined - set to 30%.");
		}
		
		percentageOfOriginalDocument = (percentageOfOriginalDocument > 0 && percentageOfOriginalDocument < 100) ? percentageOfOriginalDocument : 30d;
		
		String docText = "";		
		for(Entry<Annotation, Double> orderedSentences_SemScoreEntry : orderedSentences_SemScore.entrySet()) {
			System.out.println("Sent: " + orderedSentences_SemScoreEntry.getKey().getId() + " > SCORE: " + orderedSentences_SemScoreEntry.getValue());
			docText += ((docText.length() > 0) ? " " : "") + GtUtils.getTextOfAnnotation(orderedSentences_SemScoreEntry.getKey(), doc.getGATEdoc()); 
		}
		
		double totalLengthDoc = docText.length();
		double totalLengthSummary = 0d;
		for(Entry<Annotation, Double> orderedSentences_SemScoreEntry : orderedSentences_SemScore.entrySet()) {
			
			retMap.put(orderedSentences_SemScoreEntry.getKey(), orderedSentences_SemScoreEntry.getValue());
			
			String annoText = GtUtils.getTextOfAnnotation(orderedSentences_SemScoreEntry.getKey(), doc.getGATEdoc());
			totalLengthSummary += annoText.length();
			if( (totalLengthSummary / totalLengthDoc) > percentageOfOriginalDocument) {
				break;
			}
		}
		
		return retMap;
	}
	
	public static Map<Annotation, Double> getSummary(Map<Annotation, Double> orderedSentences_SemScore, TDDocument doc, int numSentence) {
		Map<Annotation, Double> retMap = new HashMap<Annotation, Double>();
		
		if(orderedSentences_SemScore == null || orderedSentences_SemScore.size() == 0 || doc == null) {
			return null;
		}
		
		if(numSentence < 0) {
			logger.warn("Num sentences of summary wrongly defined - set to 10.");
		}
		
		numSentence = (numSentence > 0) ? numSentence : 10;
			
		for(Entry<Annotation, Double> orderedSentences_SemScoreEntry : orderedSentences_SemScore.entrySet()) {
			System.out.println("Sent: " + orderedSentences_SemScoreEntry.getKey().getId() + " > SCORE: " + orderedSentences_SemScoreEntry.getValue());
		}
		
		int totalSentencesSummary = 0;
		for(Entry<Annotation, Double> orderedSentences_SemScoreEntry : orderedSentences_SemScore.entrySet()) {
			
			retMap.put(orderedSentences_SemScoreEntry.getKey(), orderedSentences_SemScoreEntry.getValue());
			
			totalSentencesSummary++;
			if( totalSentencesSummary >= numSentence) {
				break;
			}
		}
		
		return retMap;
	}
	
	
	public static String getStringSummaryText(Map<Annotation, Double> orderedSentences_SemScore, TDDocument doc) {
		
		if(orderedSentences_SemScore == null || orderedSentences_SemScore.size() == 0 || doc == null) {
			return null;
		}
		
		String docText = "";
		
		Set<Annotation> setAnn = orderedSentences_SemScore.keySet();
		List<Annotation> listAnn = new ArrayList<Annotation>();
		listAnn.addAll(setAnn);
		GtUtils.orderAnnotationList(listAnn, doc.getGATEdoc(), new Integer(10000000));
		
		for(Annotation summaryAnn : listAnn) {
			docText += ((docText.length() > 0) ? " " : "") + GtUtils.getTextOfAnnotation(summaryAnn, doc.getGATEdoc()); 
		}
		
		return docText;
	}
	
}