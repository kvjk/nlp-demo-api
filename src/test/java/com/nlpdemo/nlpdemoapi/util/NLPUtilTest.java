package com.nlpdemo.nlpdemoapi.util;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import opennlp.tools.util.Span;

public class NLPUtilTest {
	
	@Test
    public void SentenceDetectorTest() throws IOException {
        NLPUtil sentenceDetector = new NLPUtil();
        String[] sentences = sentenceDetector.detectSentence("If you have several test classes, you can combine them into a test suite. Running a test suite executes all test classes in that suite in the specified order. A test suite can also contain other test suites.");
        Assert.assertTrue(sentences != null && sentences.length > 0);
    }

    @Test
    public void SentencePosDetectorTest() throws IOException {
    	NLPUtil sentenceDetector = new NLPUtil();
        Span[] spans = sentenceDetector.detectSentencePos("If you have several test classes, you can combine them into a test suite. Running a test suite executes all test classes in that suite in the specified order. A test suite can also contain other test suites.");
        Assert.assertTrue(spans != null && spans.length > 0);
    }

}
		