Write a Java program to read a key-value pair from the command-line where the key may contain a series of sub-tokens separated 
by the ‘/’ character, and convert the key-value pair into a hierarchical structure containing only instances of java.util.Map and 
java.util.List according to the following rules:

1.	After tokenizing the key using the ‘/’ character, each sub-token may be either:
a.	A positive integer representing an array index, starting from 0
b.	An alphanumeric string representing a key in a map
2.	The first token in any key cannot be numeric
3.	The program is expected to perform padding within lists, such that if a numeric token is encountered with numerical value N, the resulting item must be inserted at index N in the corresponding list. 
4.	The value provided to the program must be mapped or inserted to the most deeply nested object in the hierarchy, which may be a map or a list. 

Pretty-print the resulting object to the console as a JSON-formatted string - you may use a library (e.g. Jackson) to do this.

A simple Maven build file must be provided to build the application and the application should be packaged in a JAR file named challenge.jar.

Example inputs and outputs (dependencies excluded for clarity):

1.  java -jar target/challenge.jar LINE/0/ATTRIBUTES/ServiceProfile STD

{
    "LINE": [
        {
            "ATTRIBUTES": {
                "ServiceProfile": "STD"
            }
        }
    ]
}

2.  java -jar target/challenge.jar LINE/2/ATTRIBUTES/ServiceProfile STD

{
    "LINE": [
        null,
        null,
        {
            "ATTRIBUTES": {
                "ServiceProfile": "STD"
            }
        }
    ]
}

3.  java -jar target/challenge.jar LINE/0/CFS/0/RFS/0/PORTS/1 16146

{
    "LINE": [
        {
            "CFS": [
                {
                    "RFS": [
                        {
                            "PORTS": [
                                null,
                                "16146"
                            ]
                        }
                    ]
                }
            ]
        }
    ]
}

4.  java -jar target/challenge.jar CTAG_VOIP MONO_VC

{
    "CTAG_VOIP": "MONO_VC"
}

