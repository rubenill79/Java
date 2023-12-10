package week1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day2Puzzle2 {
	static BufferedReader br;
	static final String filePath = "input2.txt";
	static ArrayList<String> games;
	static int cubesPowerSum;

	public static void main(String[] args) {
		games = readInputFromFile(filePath);

		for (String game : games) {
			cubesPowerSum += findMinimumCubes(game);
		}
		System.out.println(cubesPowerSum);
	}

	private static int findMinimumCubes(String game) {
		String[] sets = game.split(":")[1].split(";"); // Primer split se carga todo lo de antes de los : segundo split
														// pilla el numero y el color
		int tryNumber = 0;
		int currentNumber = 0;
		int[] minCubes = {0,0,0};

		for (String set : sets) {
			String[] cubes = set.trim().split("\\s+");
			for (String cube : cubes) {
				String color = "";
				try {
					tryNumber = Integer.parseInt(cube);
					// Es el numero asignar a la variable que no se va a matar
					currentNumber = tryNumber;
				} catch (NumberFormatException e) {
					// Es el color
					color = cube.toLowerCase().split(",")[0];
					if (color.equals("red") && currentNumber > minCubes[0])
						minCubes[0] = currentNumber;
					else if (color.equals("green") && currentNumber > minCubes[1])
						minCubes[1] = currentNumber;
					else if (color.equals("blue") && currentNumber > minCubes[2])
						minCubes[2] = currentNumber;

					color = "";
					tryNumber = 0;
					currentNumber = 0;
				}
			}
		}
		int totalPower = 1;
		for (int i = 0; i < minCubes.length; i++) {
			totalPower*=minCubes[i];
		}
		return totalPower;
	}

	private static ArrayList<String> readInputFromFile(String filePath) {
		ArrayList<String> lines = new ArrayList<>();

		try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
			String line;
			while ((line = reader.readLine()) != null) {
				lines.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return lines;
	}
}