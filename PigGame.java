import java.util.Scanner;

/**
 * Pig Game and Pig Statistics
 *
 * @author Katie Wang
 * @since September 13th, 2024
 *
 *        The user is prompted with two decisions. If the user chooses PigGame,
 *        run a game where the user is pitted against the computer. Both the
 *        user or computer can roll the die or hold and score the sum of the
 *        turn. The first one to score 100 points wins. If the user chooses
 *        PigStatistics, run a Monte Carlo statistical analysis to find the
 *        probability of the computer's turn ending after holding at 20 points,
 *        or failing to hold at 20 points within the user's chosen number of
 *        turns.
 * 
 */

public class PigGame {
	private final int WINNING_SCORE = 100;
	private final int COMPUTER_HOLD_THRESHOLD = 20;

	/** Print the introduction to the game */
	public void printIntroduction() {
		System.out.println("\n");
		System.out.println("______ _         _____");
		System.out.println("| ___ (_)       |  __ \\");
		System.out.println("| |_/ /_  __ _  | |  \\/ __ _ _ __ ___   ___");
		System.out.println("|  __/| |/ _` | | | __ / _` | '_ ` _ \\ / _ \\");
		System.out.println("| |   | | (_| | | |_\\ \\ (_| | | | | | |  __/");
		System.out.println("\\_|   |_|\\__, |  \\____/\\__,_|_| |_| |_|\\___|");
		System.out.println("          __/ |");
		System.out.println("         |___/");
		System.out.println("\nThe Pig Game is human vs computer. Each takes a"
				+ " turn rolling a die and the first to score");
		System.out.println("100 points wins. A player can either ROLL or "
				+ "HOLD. A turn works this way:");
		System.out.println("\n\tROLL:\t2 through 6: add points to turn total, "
				+ "player's turn continues");
		System.out.println("\t\t1: player loses turn");
		System.out.println("\tHOLD:\tturn total is added to player's score, "
				+ "turn goes to other player");
		System.out.println("\n");
	}

	public static void main(String[] args) {
		PigGame newGame = new PigGame(); // Creating a instance of PigGame
		Scanner enterTurn = new Scanner(System.in); // Next computer turn scanner
		Scanner numTurns = new Scanner(System.in); // Number of turns scanner
		Boolean userTurn = true; // The user is playing the game
		Boolean compTurn = false; // The computer is playing the same
		int userCurrentScore = 0; // The user's current turn score
		int userTotalScore = 0; // The user's total score
		int compCurrentScore = 0; // The computer's current turn score
		int compTotalScore = 0; // The computer's total score
		char decision; // The user's input character
		int turns = 0; // Number of statistic turns
		int[] points = new int[26]; // Count of points computer rolls

		Dice pigDice = new Dice(); // Creating a new instance of a Dice
		newGame.printIntroduction(); // Call the method to print the introduction

		// Asks the user to either play game or statistics
		decision = Prompt.getChar("Play game or Statistics (p or s)");

		if (decision == 'p') { // User plays the game

			// User and computer's total score is less than win score
			while (userTotalScore < newGame.WINNING_SCORE && compTotalScore < newGame.WINNING_SCORE) {

				// User's turn
				while (userTurn == true) {

					// If it's the start of a roll, print out the start info
					if (userCurrentScore == 0) {
						System.out.println(); // adding spacing
						System.out.println("**** USER Turn *** \n");
						System.out.println("Your turn score:   " + userCurrentScore);
						System.out.println("Your total score:   " + userTotalScore + "\n");
					}

					// Asks the user to either roll or hold
					decision = Prompt.getChar("(r)oll or (h)old");

					// If the user enters an 'h' (user holds)
					if (decision == 'h') {
						System.out.println(); // adding spacing

						// Add the current score to the total score
						userTotalScore += userCurrentScore;

						// Print out the total score
						System.out.println("You HOLD");
						System.out.println("Your total score:   " + userTotalScore + "\n");
						System.out.println(); // adding spacing

						// User's current score is reset to 0
						userCurrentScore = 0;

						/*
						 * If the user's total score is greater than 100
						 * exit out of the user's turn
						 */
						if (userTotalScore >= newGame.WINNING_SCORE) {
							break;
						}

						// Move to computer's turn
						userTurn = false;
						compTurn = true;

					} else {
						// If the user enters an 'r' (user holds)
						System.out.println(); // adding spacing
						System.out.println("You ROLL \n");

						/*
						 * Call the method to roll and add the roll
						 * to the user's current score
						 */
						userCurrentScore += pigDice.roll();

						// Call the method to print out the ASCII dice image
						pigDice.printDice();

						// Ends the user's turn if the dice roll equals 1
						if (pigDice.getValue() == 1) {

							// Print out the total score of the user
							System.out.println("You LOSE your turn");
							System.out.println("Your total score:   " + userTotalScore + "\n");
							System.out.println(); // adding spacing

							// User's current score is reset to 0
							userCurrentScore = 0;

							// Move to the computer's turn
							userTurn = false;
							compTurn = true;
							break;
						}

						// Print out the current score and the total score
						System.out.println("Your turn score:   " + userCurrentScore);
						System.out.println("Your total score:   " + userTotalScore + "\n");
					}

				}

				// The computer's turn
				while (compTurn == true) {

					// If it's the start of a roll, print out the start info
					if (compCurrentScore == 0) {
						System.out.println("**** COMPUTER'S Turn ***\n");
						System.out.println("Computer's turn score:   " + compCurrentScore);
						System.out.println("Computer's total score:   " + compTotalScore + "\n");
					}

					// Current score + the next roll is less than the threshold
					if (compCurrentScore + pigDice.roll() < newGame.COMPUTER_HOLD_THRESHOLD) {

						// Ask user to press enter for computer to run
						System.out.println("Press enter for computer's turn -> ");
						// Scanner will read the enter
						enterTurn.nextLine();

						// Print that the computer is rolling
						System.out.println("Computer will ROLL");

						/*
						 * Call the method to get value of the roll and
						 * add the value to the computer's current score
						 */
						compCurrentScore += pigDice.getValue();

						pigDice.printDice(); // print out the dice face

						// Ends the computer's turn if the dice roll equals 1
						if (pigDice.getValue() == 1) {

							// Print out the computer's total score
							System.out.println("Computer's total score:   " + compTotalScore + "\n");

							// Computer's current score is reset to 0
							compCurrentScore = 0;

							// Move to the user's turn
							userTurn = true;
							compTurn = false;
						}

						// Print out the current score and the total score
						System.out.println("Computer's turn score:   " + compCurrentScore);
						System.out.println("Computer's total score:   " + compTotalScore + "\n");

					} else {
						// Current score + the next roll is greater than threshold

						// Ask user to press enter for computer to run
						System.out.println("Press enter for computer's turn -> ");
						// Scanner will read the enter
						enterTurn.nextLine();

						// Print that the computer is rolling
						System.out.println("Computer will ROLL");

						// Call the method to print out the ASCII dice image
						pigDice.printDice();

						// Print that the computer is holding
						System.out.println("Computer will HOLD");

						/*
						 * Add the computer's current score and the next roll's
						 * score to the computer's total score
						 */
						compTotalScore += compCurrentScore + pigDice.getValue();

						// Print out the computer's total score
						System.out.println("Computer's total score:   " + compTotalScore + "\n");

						// Computer's current score is reset to 0
						compCurrentScore = 0;

						/*
						 * If the computer's total score is
						 * greater than 100
						 */
						if (compTotalScore >= newGame.WINNING_SCORE) {
							break;
						}

						// Move to the user's turn
						userTurn = true;
						compTurn = false;

					}
				}
			}

			// The user wins!
			if (userTotalScore >= newGame.WINNING_SCORE) {
				System.out.println("Congratulations!!! YOU WON!!!!");
				System.out.println(); // adding spacing
				System.out.println("Thanks for playing the Pig Game!!!");
				System.out.println(); // adding spacing
			} else { // The computer wins :(
				System.out.println("Too bad. COMPUTER WON");
				System.out.println(); // adding spacing
				System.out.println("Thanks for playing the Pig Game!!!");
				System.out.println(); // adding spacing
			}

			// close the line scanner
			enterTurn.close();

		} else { // User is running statistics
			// Starting the statistic test
			System.out.println(); // adding spacing
			System.out.println("Run statistical analysis - 'Hold at 20'\n");

			// Asks the user for number of turns to perform]
			System.out.print("Number of turns (1000 - 1000000) -> ");
			turns = numTurns.nextInt();

			// if the user's turn input value is not in bounds
			while (!(turns >= 1000 && turns <= 1000000)) {
				System.out.print("Number of turns (1000 - 1000000) -> ");
				turns = numTurns.nextInt();
			}

			System.out.println(); // adding spacing

			// Loop through the number of turns
			for (int i = 0; i < turns; i++) {
				// while the computer's current score is less than 20
				while (compCurrentScore < newGame.COMPUTER_HOLD_THRESHOLD) {
					/*
					 * Call the method to get value of the roll and
					 * add the value to the computer's current score
					 */
					compCurrentScore += pigDice.roll();

					// Ends the turn if the dice roll equals 1
					if (pigDice.getValue() == 1) {
						// Computer's current score is reset to 0
						compCurrentScore = 0;
						break;
					}
				}

				// add a point to the corresponding array index
				points[compCurrentScore] += 1;

				// Computer's current score is reset to 0
				compCurrentScore = 0;
			}

			// Printing out the probabilities
			System.out.println("       Estimated \n Score Probability");
			System.out.printf(" 0  %.5f%n", (double) points[0] / turns);
			System.out.printf(" 20 %.5f%n", (double) points[20] / turns);
			System.out.printf(" 21 %.5f%n", (double) points[21] / turns);
			System.out.printf(" 22 %.5f%n", (double) points[22] / turns);
			System.out.printf(" 23 %.5f%n", (double) points[23] / turns);
			System.out.printf(" 24 %.5f%n", (double) points[24] / turns);
			System.out.printf(" 25 %.5f%n", (double) points[25] / turns);
			System.out.println();

			// close the int scanner
			numTurns.close();
		}

	}

}
