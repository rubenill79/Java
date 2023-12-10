package week1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day2Puzzle1 {

	static BufferedReader br;
	static final String filePath = "input2.txt";
	static ArrayList<String> games;
	static int[] maxCubes = { 12, 13, 14 };
	static int impossibleGamesIDS;

	public static void main(String[] args) {
		games = readInputFromFile(filePath);

		for (String game : games) {
			if (isPossibleGame(game, maxCubes)) {
				impossibleGamesIDS += extractGameID(game);
			}
		}
		System.out.println(impossibleGamesIDS);
	}

	private static boolean isPossibleGame(String game, int[] maxCubes) {
		String[] sets = game.split(":")[1].split(";"); // Primer split se carga todo lo de antes de los : segundo split
														// pilla el numero y el color
		int tryNumber = 0;
		int currentNumber = 0;

		for (String set : sets) {
			String[] cubes = set.trim().split("\\s+");
			for (String cube : cubes) {
				String color = "";
				System.out.println(cube);
				try {
					tryNumber = Integer.parseInt(cube);
					// Es el numero asignar a la variable que no se va a matar
					currentNumber = tryNumber;
				} catch (NumberFormatException e) {
					// Es el color
					color = cube.toLowerCase().split(",")[0];
					if (color.equals("red") && currentNumber > maxCubes[0])
						return false;
					else if (color.equals("green") && currentNumber > maxCubes[1])
						return false;
					else if (color.equals("blue") && currentNumber > maxCubes[2])
						return false;

					color = "";
					tryNumber = 0;
					currentNumber = 0;
				}
			}
		}
		return true;
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

	private static int extractGameID(String game) {
		return Integer.parseInt(game.split(":")[0].replaceAll("\\D", "")); // \\D "" quita todos los caracteres no
																			// numericos
	}
}