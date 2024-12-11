package hutchison.grant;

import java.util.ArrayList;
import java.util.Scanner;

public class Driver {

	public static void main(String[] args) {
		// setup game components
		Die d1 = new Die();
		Die d2 = new Die();

		Tile[] tiles = new Tile[9];

		// populate the array with tiles
		for (int i = 0; i < tiles.length; i++) {
			tiles[i] = new Tile((i + 1));
		}

		// start the game
		boolean gameOver = false;
		while (!gameOver) {

			printTiles(tiles);

			int diceSum = d1.roll() + d2.roll();
			System.out.println("Roll is: " + diceSum);

			Scanner in = new Scanner(System.in);

			// List to hold the instructions
			ArrayList<Integer> instructions = new ArrayList<>();

			boolean valid = false;

			while (!valid) {
				// ask for instructions
				System.out.println("Which tiles? Separate with spaces.");
				String data = in.nextLine();
				String[] values = data.split(" "); // split on spaces
				int inputSum = 0;

				// storing instructions and calculating sum of instructions
				instructions.clear();
				for (String value : values) {
					int n = Integer.valueOf(value);
					inputSum += n;
					instructions.add(n);
				}

				// checking validity of instructions
				if (inputSum == diceSum) {
					valid = true;
				} else if (inputSum==-1) {
					gameOver = true;
					break;
				}
				else {
					System.out.println("Incorrect instructions.");
				}
			} // end of while loop

			if (instructions.get(0) != -1) {
				// process instructions
				for (int instruction : instructions) {
					tiles[instruction - 1].putDown();
				}
			}
			
		}
		
		// determine the result for the round
		int roundTotal = 0;
		for (Tile t :tiles ) {
			if (t.isUp()) {
				roundTotal += t.getValue();
			}
		}
		System.out.println("Total this round is " + roundTotal);
		
		System.out.println("Thank you for playing");
	}

	private static void printTiles(Tile[] tiles) {
		for (Tile t : tiles) {
			System.out.print(t);
		}
		System.out.println();
	}

}
