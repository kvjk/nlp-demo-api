package com.nlpdemo.nlpdemoapi.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.stream.Collectors;

import opennlp.tools.sentdetect.SentenceDetector;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.util.Span;


public class NLPUtil {
	
    public String[] detectSentence(String paragraph) throws IOException {
	
        InputStream modelIn = getClass().getResourceAsStream("/models/en-sent.bin");
        final SentenceModel sentenceModel = new SentenceModel(modelIn);
        modelIn.close();

        SentenceDetector sentenceDetector = new SentenceDetectorME(sentenceModel);
        String sentences[] = sentenceDetector.sentDetect(paragraph);
        for (String sent : sentences) {
            System.err.println(sent);
        }
        return sentences;	
    }
    
    public Span[] detectSentencePos(String paragraph) throws IOException {

        InputStream modelIn = getClass().getResourceAsStream("/en-sent.bin");
            final SentenceModel sentenceModel = new SentenceModel(modelIn);
            modelIn.close();
        SentenceDetector sentenceDetector = new SentenceDetectorME(sentenceModel);
            Span[] spans = sentenceDetector.sentPosDetect(paragraph);
            for (Span span : spans) {
                System.err.println(span);
            }
        return spans;
    }   
    
    public String truncate3N5Sentences(String text) throws Exception{
    	StringBuilder truncate3N5Sentences = new StringBuilder(text);
    	Span[] spans = detectSentencePos(text);   
    	if(spans.length >= 3)
    	truncate3N5Sentences = truncate3N5Sentences.delete(spans[2].getStart(), spans[2].getEnd());
    	spans = detectSentencePos(truncate3N5Sentences.toString());
    	if(spans.length >= 4)
    	truncate3N5Sentences = truncate3N5Sentences.delete(spans[3].getStart(), spans[3].getEnd());
    	return truncate3N5Sentences.toString();
    }
    

  
    public static void main(String[] args) throws Exception{
    	
        NLPUtil sentenceDetector = new NLPUtil();
        String[] sentences = sentenceDetector.detectSentence("If you have several test classes, you can combine them into a test suite. Running a test suite executes all test classes in that suite in the specified order. A test suite can also contain other test suites.");

		
	}

}
