package com.nlpdemo.nlpdemoapi.util;

import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.tokenize.SimpleTokenizer;		
	
public class WordFrequencyCounterUtil {
		
	public static Map<String, Integer> getCountsMap(String text){
        SimpleTokenizer tokenizer = SimpleTokenizer.INSTANCE;
        String[] tokens = tokenizer.tokenize(text);
        List <String> list = Arrays.asList(tokens);
		
	 
	        Map <String, Integer > wordCounter = list.stream()
	            .collect(Collectors.toMap(w -> w.toLowerCase(), w -> 1, Integer::sum));	
	        return wordCounter;
	}
	
	public  Map<String, Integer> getPOSCountsMap(String text) throws Exception{
        SimpleTokenizer tokenizer = SimpleTokenizer.INSTANCE;
        String[] tokens = tokenizer.tokenize(text);

        //TODO:fix lazy loading to fix performace issue
        InputStream inputStreamPOSTagger = getClass().getResourceAsStream("/models/en-pos-maxent.bin");
        POSModel posModel = new POSModel(inputStreamPOSTagger);
        POSTaggerME posTagger = new POSTaggerME(posModel);
        String tags[] = posTagger.tag(tokens);
        List <String> list = Arrays.asList(tags);
	 
	    Map <String, Integer > wordCounter = list.stream().collect(Collectors.toMap(w -> w.toUpperCase(), w -> 1, Integer::sum));
//	    wordCounter = replaceNullValues(wordCounter, new Integer(0));
	    return wordCounter;
	}	
	
    public <T, K> Map<K, T> replaceNullValues(Map<K, T> map, T defaultValue) 
    { 
  
        // Replace the null value 
        map = map.entrySet() 
                  .stream() 
                  .map(entry -> { 
                      if (entry.getValue() == null) 
                          entry.setValue(defaultValue); 
                      return entry; 
                  }) 
                  .collect(Collectors.toMap(Map.Entry::getKey, 
                                            Map.Entry::getValue)); 
  
        return map; 
    } 

}
