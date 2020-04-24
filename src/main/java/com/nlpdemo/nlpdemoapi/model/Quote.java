package com.nlpdemo.nlpdemoapi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Transient;

import com.nlpdemo.nlpdemoapi.util.NLPUtil;
import com.nlpdemo.nlpdemoapi.util.WordFrequencyCounterUtil;

@Entity
public class Quote {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Lob
	private String text;

//    @Transient
//    private String[] sentences;

    @Transient
    private String withOut3N5;
    
	@Transient
	private String nounsCount;

	@Transient
	private String verbsCount;

	@Transient
	private String periodsCount;

	@Transient
	private String youCount;

	@Transient
	private String thatCount;

	@Transient
	private String thingCount;

	@Transient
	private String theyCount;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	
	public String getWithOut3N5() throws Exception {
		return new NLPUtil().truncate3N5Sentences(text);
	}

	/*
	 * public String[] getSentences() throws Exception{ return new
	 * NLPUtil().detectSentence(text); }
	 */
	public String getNounsCount() throws Exception {
		WordFrequencyCounterUtil wordFrequencyCounterUtil = new WordFrequencyCounterUtil();
		if (wordFrequencyCounterUtil != null && wordFrequencyCounterUtil.getPOSCountsMap(text).get("NN") != null) {
			return wordFrequencyCounterUtil.getPOSCountsMap(text).get("NN").toString();
		}
		return "0";
		
	}

	public String getVerbsCount() throws Exception {
		
		WordFrequencyCounterUtil wordFrequencyCounterUtil = new WordFrequencyCounterUtil();
		int count = 0;
		
		if(wordFrequencyCounterUtil.getPOSCountsMap(text).get("VB") != null) 
			count = count + wordFrequencyCounterUtil.getPOSCountsMap(text).get("VB") ;
		if(wordFrequencyCounterUtil.getPOSCountsMap(text).get("VBD") != null) 
			count = count + wordFrequencyCounterUtil.getPOSCountsMap(text).get("VBD") ;
		if(wordFrequencyCounterUtil.getPOSCountsMap(text).get("VBZ") != null) 
			count = count + wordFrequencyCounterUtil.getPOSCountsMap(text).get("VBZ") ;					  
				  
		  return String.valueOf(count);
	}

	public String getPeriodsCount() throws Exception {
//		return new WordFrequencyCounterUtil().getPOSCountsMap(text).get(".").toString();
		return String.valueOf(text.chars().filter(ch -> ch == '.').count());
	}

	public String getYouCount() {
		if (WordFrequencyCounterUtil.getCountsMap(text).get("you") != null) {
			return WordFrequencyCounterUtil.getCountsMap(text).get("you").toString();
		}
		return "0";
	}

	public String getThatCount() {
		if (WordFrequencyCounterUtil.getCountsMap(text).get("that") != null) {
			return WordFrequencyCounterUtil.getCountsMap(text).get("that").toString();
		}
		return "0";
	}

	public String getThingCount() {
		if (WordFrequencyCounterUtil.getCountsMap(text).get("thing") != null) {
			return WordFrequencyCounterUtil.getCountsMap(text).get("thing").toString();
		}
		return "0";
	}

	public String getTheyCount() {
		if (WordFrequencyCounterUtil.getCountsMap(text).get("they") != null) {
			return WordFrequencyCounterUtil.getCountsMap(text).get("they").toString();
		}
		return "0";
	}

}
