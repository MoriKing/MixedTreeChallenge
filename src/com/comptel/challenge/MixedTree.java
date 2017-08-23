package com.comptel.challenge;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;

public class MixedTree {
	
	private Object mixedTreeRoot;
	
	//Receiving the tokens for creating a mixed tree
	public MixedTree(String[] tokens,String value){
		this.mixedTreeRoot= this.create(tokens,0,value);
	}
	
	//Creates the MixedTree hierarchy recursively based on the given path tokens and value
	private Object create(String[] tokens, int tokenOffset, String value) {

		if (isMap(tokens[tokenOffset])) {
			HashMap<String, Object> returnMap = new HashMap<String, Object>();
			if (tokenOffset + 1 >= tokens.length) {
				returnMap.put(tokens[tokenOffset], value);
				return returnMap;
			} else {
				returnMap.put(tokens[tokenOffset], create(tokens, tokenOffset + 1, value));
				return returnMap;
			}
		} else {
			int padding = Integer.parseInt(tokens[tokenOffset]);
			ArrayList<Object> returnArray = new ArrayList<Object>();
			while (padding > 0) {
				returnArray.add(null);
				padding--;
			}

			if (tokenOffset + 1 >= tokens.length) {
				returnArray.add(value);
				return returnArray;
			} else {
				returnArray.add(create(tokens, tokenOffset + 1, value));
				return returnArray;
			}

		}
	}

	//public getter for JSON format
	public JsonNode getJsonFormat(){
		return GetJsonFormat(this.mixedTreeRoot);
	}
	
	//Parses the given MixedTree structure recursively in Depth First Traverse and returns the JsonNode format
	@SuppressWarnings("unchecked")
	private JsonNode GetJsonFormat(Object mixedTree) {

		if (mixedTree == null) {
			return null;
		} else if (mixedTree.getClass().getName().equals("java.lang.String")) {
			return TextNode.valueOf((String) mixedTree);
		} // ENDIF
		else if (mixedTree.getClass().getName().equals("java.util.HashMap")) {
			ObjectMapper mapper = new ObjectMapper();
			JsonNode jsonNode = mapper.createObjectNode();
			HashMap<String, Object> copyMapList = (HashMap<String, Object>) mixedTree;
			Iterator<Entry<String, Object>> it = copyMapList.entrySet().iterator();
			while (it.hasNext()) {
				@SuppressWarnings("rawtypes")
				Map.Entry pair = (Map.Entry) it.next();
				((ObjectNode) jsonNode).set((String) pair.getKey(), GetJsonFormat(pair.getValue()));
				it.remove(); // avoids a ConcurrentModificationException
			}
			return jsonNode;
		} // ENDIF
		else if (mixedTree.getClass().getName().equals("java.util.ArrayList")) {
			ObjectMapper mapper = new ObjectMapper();
			JsonNode jsonNode = mapper.createArrayNode();
			for (Object mapListObject : (ArrayList<Object>) mixedTree) {
				((ArrayNode) jsonNode).add(GetJsonFormat(mapListObject));
			}
			return jsonNode;
		} // ENDIF

		return null;
	}
	
	private boolean isMap(String string) {

		if (Character.isDigit(string.charAt(0))) {
			return false;
		} else {
			return true;
		}

	}
	
	
	/**
	 * @return the root
	 */
	public Object getRoot() {
		return mixedTreeRoot;
	}

	
}
