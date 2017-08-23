package com.comptel.challenge;

public class Main {

	public static void main(String[] args) {
		
				Parser myParser = new Parser();
				myParser.parseTokens(args[0]);
				myParser.setValue(args[1]);
				
				if(myParser.validateFristToken()){
					MixedTree mixedTree = new MixedTree(myParser.gettokens(), myParser.getValue());
					myParser.prettyPrint(mixedTree.getJsonFormat());
				}
	}
}
