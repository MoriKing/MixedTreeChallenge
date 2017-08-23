package com.comptel.challenge;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.util.DefaultIndenter;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter.Indenter;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;;

public class Parser {

	private String[] tokens;
	private String value;

	public void parseTokens(String tokens) {
		
		this.tokens = tokens.split("/");
	}

	// The first token in any key cannot be numeric
	public boolean validateFristToken() {

		for (char character : this.tokens[0].toCharArray()) {
			if (Character.isDigit(character)) {
				System.out.println("First token contains a number! please try again!");
				return false;
			}
		}
		return true;
	}

	public void prettyPrint(JsonNode node) {
		
		ObjectMapper mapper = new ObjectMapper();
		DefaultPrettyPrinter printer = new DefaultPrettyPrinter();
		Indenter indenter = new DefaultIndenter().withLinefeed(System.getProperty("line.separator"));
		printer.indentArraysWith(indenter); // Indent JSON arrays
		String jsonString = "";
		try {
			jsonString = mapper.writer(printer).writeValueAsString(node);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		System.out.println(jsonString);
	}

	/**
	 * @return the tokens
	 */
	public String[] gettokens() {
		return tokens;
	}
	
	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value
	 *            the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

}
