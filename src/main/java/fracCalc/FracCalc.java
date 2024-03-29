/**
 * @author Mr. Rasmussen
 */

package fracCalc;

import java.util.Scanner;

public class FracCalc {

	public static void main(String[] args) {
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

	// ** IMPORTANT ** DO NOT DELETE THIS FUNCTION. This function will be used to
	// test your code
	// This function takes a String 'input' and produces the result
	//
	// input is a fraction string that needs to be evaluated. For your program, this
	// will be the user input.
	// e.g. input ==> "1/2 + 3/4"
	//
	// The function should return the result of the fraction after it has been
	// calculated
	// e.g. return ==> "1_1/4"
	public static String produceAnswer(String input) {
		// TODO: Implement this function to produce the solution to the input

		// splitting up the input below
		int lastSpace = input.lastIndexOf(' ');

		String lastFrac = input.substring(lastSpace + 1, input.length());

		int firstSpace = input.indexOf(' ');

		if (firstSpace == -1 || firstSpace == lastSpace)
			return "ERROR: Please provide a space between operands and operators.";

		String firstFrac = input.substring(0, firstSpace);

		String operator = input.substring(firstSpace + 1, lastSpace);

		// splitting up the first fraction below

		int firstUnder = firstFrac.indexOf('_');
		int firstSlash = firstFrac.indexOf('/');

		int firstWhole = wholeNum(firstUnder, firstSlash, firstFrac);
		int firstNumer = numer(firstUnder, firstSlash, firstFrac);
		int firstDenom = denom(firstUnder, firstSlash, firstFrac);

		// splitting up the last fraction below

		int lastUnder = lastFrac.indexOf('_');
		int lastSlash = lastFrac.indexOf('/');

		int lastWhole = wholeNum(lastUnder, lastSlash, lastFrac);
		int lastNumer = numer(lastUnder, lastSlash, lastFrac);
		int lastDenom = denom(lastUnder, lastSlash, lastFrac);

		String answer = computeFrac(firstWhole, firstNumer, firstDenom, lastWhole, lastNumer, lastDenom, operator);

		return answer;

	}

	// TODO: Fill in the space below with any helper methods that you think you will
	// need

	// this gives the whole number of a fraction
	public static int wholeNum(int under, int slash, String frac) {
		int whole;

		if (under != -1 && slash != -1) {
			whole = Integer.parseInt(frac.substring(0, under));
		} else if (slash != -1 && under == -1) {
			whole = 0;
		} else {
			whole = Integer.parseInt(frac);
		}

		return whole;
	}

	// this gives the numerator of a fraction
	public static int numer(int under, int slash, String frac) {
		int numer;

		if (slash != -1) {
			numer = Integer.parseInt(frac.substring(under + 1, slash));
		} else {
			numer = 0;
		}

		return numer;
	}

	// this gives the denominator of a fraction
	public static int denom(int under, int slash, String frac) {
		int denom;

		if (slash != -1) {
			denom = Integer.parseInt(frac.substring(slash + 1, frac.length()));
		} else {
			denom = 1;
		}

		return denom;
	}

	// this computes the final fraction
	public static String computeFrac(int w1, int n1, int d1, int w2, int n2, int d2, String oper) {

		int totalNumer = 0;
		int totalDenom = d1 * d2;

		if (oper.equals("+")) {
			totalNumer = (w1 * d1 + n1) * d2 + (w2 * d2 + n2) * d1;
		} else if (oper.equals("-")) {
			totalNumer = (w1 * d1 + n1) * d2 - (w2 * d2 + n2) * d1;
		} else if (oper.equals("*")) {
			totalNumer = (w1 * d1 + n1) * (w2 * d2 + n2);
		} else if (oper.equals("/")) {
			totalNumer = (w1 * d1 + n1) * d2;
			totalDenom = (w2 * d2 + n2) * d1;
		} else {
			return "ERROR: Please use one (and only one) of the following operators: "
					+ "+, -, *, / between operands and " + "provide only one space between operands and operators.";
		}

		if (totalDenom == 0)
			return "ERROR: Cannot divide by zero.";

		return simplify(totalNumer, totalDenom);
	}

	// n - numerator
	// d - denominator
	// returns simplified fraction
	public static String simplify(int n, int d) {
		String fraction = "";
		
		// w - whole number
		int w = 0;
		String whole = "";

		if (n != 0) {
			int gcd = gcd(n, d);

			n = n / gcd;
			d = d / gcd;

			w = n / d;
			n = n % d;
			if (w != 0)
				whole = w + "_";

			if (n != 0)
				fraction = n + "/" + d;

			if (n == 0)
				whole = "" + w;

		} else {
			fraction = "0";
		}

		return whole + fraction; 
	}

	// finds the greatest common denominator for two integers
	public static int gcd(int a, int b) {

		int gcd = 1;

		a = Math.abs(a);
		b = Math.abs(b);

		for (int i = 1; i < Math.min(a, b); i++) {
			if (a % i == 0 && b % i == 0)
				gcd = i;
		}

		return gcd;

	}
}
