import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Day1Puzzle1 {
	
	static BufferedReader br;
	static final String filePath = "input.txt";
	
	public static void main(String[] args) throws IOException {
		int totalValue = 0;
		int firstDigitValue = 0;
		int lastDigitValue = 0;
		String linea;
		char[] caracteres;
		
		try {
			br = new BufferedReader(new FileReader(filePath));
			while ((linea = br.readLine()) != null) {
				caracteres = linea.toCharArray();
				for (int i = 0; i < caracteres.length; i++) {
					String c = caracteres[i] + "";
			        try {
			            int num = Integer.parseInt(c);
			            if(firstDigitValue == 0) firstDigitValue = num;
			            else lastDigitValue = num;
			        } catch (NumberFormatException e) {
			            // La conversión a número falló
			        }
			        if(i == caracteres.length-1) {
			        	if(lastDigitValue == 0) lastDigitValue = firstDigitValue;
			        	String value = firstDigitValue + "" + lastDigitValue;
			        	totalValue += Integer.parseInt(value);
			        	firstDigitValue = 0;
			        	lastDigitValue = 0;
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
