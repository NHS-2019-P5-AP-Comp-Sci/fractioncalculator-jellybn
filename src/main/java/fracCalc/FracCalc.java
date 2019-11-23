/**
 * @author Mr. Rasmussen
 */

package fracCalc;

import java.util.Scanner;

public class FracCalc {

    public static void main(String[] args)
    {
        // TODO: Read the input from the user and call produceAnswer with an equation
    	Scanner userInput = new Scanner(System.in);
    	
    	String input = userInput.nextLine();
    	
    	while (!input.equals("quit")) {
    		String answer = produceAnswer(input);
    		System.out.println(answer);
        	input = userInput.nextLine();
    	}
    	    	    	
    	userInput.close();
    }

    // ** IMPORTANT ** DO NOT DELETE THIS FUNCTION.  This function will be used to test your code
    // This function takes a String 'input' and produces the result
    //
    // input is a fraction string that needs to be evaluated.  For your program, this will be the user input.
    //      e.g. input ==> "1/2 + 3/4"
    //
    // The function should return the result of the fraction after it has been calculated
    //      e.g. return ==> "1_1/4"
    public static String produceAnswer(String input)
    {
        // TODO: Implement this function to produce the solution to the input
    	
    	//splitting up the input below
    	int lastSpace = input.lastIndexOf(' ');
    	String lastFrac = input.substring(lastSpace + 1, input.length());
    	
    	int firstSpace = input.indexOf(' ');
    	String firstFrac = input.substring(0, firstSpace);
    	
    	String operator = input.substring(firstSpace + 1, lastSpace);
    	
    	//splitting up the first fraction below
    	
    	int firstUnder = firstFrac.indexOf('_');
    	int firstSlash = firstFrac.indexOf('/');
    	
    	int firstWhole = wholeNum(firstUnder, firstSlash, firstFrac);
    	int firstNumer = numer(firstUnder, firstSlash, firstFrac);
    	int firstDenom = denom(firstUnder, firstSlash, firstFrac);
    	
    	String answer1 = splitFrac(firstUnder, firstSlash, firstFrac);
    	
    	//splitting up the last fraction below
    	
    	int lastUnder = lastFrac.indexOf('_');
    	int lastSlash = lastFrac.indexOf('/');
    	
    	int lastWhole = wholeNum(lastUnder, lastSlash, lastFrac);
    	int lastNumer = numer(lastUnder, lastSlash, lastFrac);
    	int lastDenom = denom(lastUnder, lastSlash, lastFrac);
    	
    	String answer2 = splitFrac(lastUnder, lastSlash, lastFrac);
    	
        return answer2;
    }
    
    
    // TODO: Fill in the space below with any helper methods that you think you will need
    
    // this splits the fraction and provides the string result
    public static String splitFrac(int under, int slash, String frac) {
    	String answer;
    	
    	if (under != -1 && slash != -1) {
	    	answer = "whole:" + frac.substring(0, under) +
	    			" numerator:" + frac.substring(under + 1, slash) +
	    			" denominator:" + frac.substring(slash + 1, frac.length());
    	} else if (slash != -1 && under == -1) {
    		answer = "whole:" + 0 +
	    			" numerator:" + frac.substring(under + 1, slash) +
	    			" denominator:" + frac.substring(slash + 1, frac.length());
    	} else {
    		answer = "whole:" + frac +
	    			" numerator:" + 0 +
	    			" denominator:" + 1;
    	}
    	
    	return answer;
    }
    
    //this gives the whole number of a fraction
    public static int wholeNum(int under, int slash, String frac) {
    	int whole;
    	
    	if (under != -1 && slash != -1) {
	    	whole =  Integer.parseInt(frac.substring(0, under));
    	} else if (slash != -1 && under == -1) {
    		whole = 0;
    	} else {
    		whole = Integer.parseInt(frac);
    	}
    	
    	return whole;
    }
    
    //this gives the numerator of a fraction
    public static int numer(int under, int slash, String frac) {
    	int numer;
    	
    	if (slash != -1) {
	    	numer =  Integer.parseInt(frac.substring(under + 1, slash));
    	} else {
    		numer = 0;
    	}
    	
    	return numer;
    }

    //this gives the denominator of a fraction
    public static int denom(int under, int slash, String frac) {
    	int denom;
    	
    	if (slash != -1) {
	    	denom =  Integer.parseInt(frac.substring(slash + 1, frac.length()));
    	} else {
    		denom = 1;
    	}
    	
    	return denom;
    }
}
