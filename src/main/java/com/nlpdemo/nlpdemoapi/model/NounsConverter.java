package com.nlpdemo.nlpdemoapi.model;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class NounsConverter implements AttributeConverter<String, String>{
	
	@Override
	public String convertToDatabaseColumn(String attribute) {
		// TODO Auto-generated method stub
		return "NA";
	}
	
	@Override
	public String convertToEntityAttribute(String dbData) {
		// TODO Auto-generated method stub
		return "NA";
	}

}
	