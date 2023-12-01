import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Day1Puzzle2 {
	
	static BufferedReader br;
	static String[] numerosString = {"one","two","three","four","five","six","seven","eight","nine"};
	static int[] numerosInt = {1,2,3,4,5,6,7,8,9};
	static final String filePath = "input.txt";
	
	public static void main(String[] args) throws IOException {
		int totalValue = 0;
		int firstDigitValue = 0;
		int lastDigitValue = 0;
		String linea;
		String expresion = "";
		char[] caracteres;
		
		try {
			br = new BufferedReader(new FileReader(filePath));
			while ((linea = br.readLine()) != null) {
				caracteres = linea.toCharArray();
				for (int i = 0; i < caracteres.length; i++) {
					String c = caracteres[i] + "";
			        try {
			            int num = Integer.parseInt(c);
			            if(firstDigitValue == 0) {
			            	firstDigitValue = num;
			            	expresion = "";
			            }
			            else {
			            	lastDigitValue = num;
			            	expresion = "";
			            }
			        } catch (NumberFormatException e) {
						expresion +=c;
			            for (int j = 0; j < numerosString.length; j++) {
							if(expresion.endsWith(numerosString[j])) {
					            if(firstDigitValue == 0) {
					            	firstDigitValue = numerosInt[j];
					            	break;
					            }
					            else {
					            	lastDigitValue = numerosInt[j];
					            	break;
					            }
							}
						}
			        }
			        if(i == caracteres.length-1) {
			        	if(lastDigitValue == 0) lastDigitValue = firstDigitValue;
			        	String value = firstDigitValue + "" + lastDigitValue;
			        	totalValue += Integer.parseInt(value);
			        	firstDigitValue = 0;
			        	lastDigitValue = 0;
			        	expresion = "";
			        }
				}
			}
			System.out.println(totalValue);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
