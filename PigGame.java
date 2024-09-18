import java.util.Scanner;
/**
 *	The game of Pig.
 *
 *	@author	Katie Wang
 *	@since September 13th, 2024 
 *
 *        Runs a game where the user is pitted against the computer. Both the 
 *        user or computer can roll the die or hold and score the sum of the turn.
 *        The first one to score 100 points wins. 
 */

public class PigGame {

	/**	Print the introduction to the game */
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
		PigGame newGame = new PigGame(); 			// Creating a instance of PigGame
		Scanner enterTurn = new Scanner(System.in); // Creating a scanner
		Boolean userTurn = true; 					// The user is playing the game
		Boolean compTurn = false; 					// The computer is playing the same 
		int userCurrentScore = 0; 					// The user's current turn score 
		int userTotalScore = 0; 					// The user's total score 
		int compCurrentScore = 0; 					// The computer's current turn score 
		int compTotalScore = 0; 					// The computer's total score 
		char decision;								// The user's input character
		
		Dice pigDice = new Dice();   // Creating a new instance of a Dice
		newGame.printIntroduction(); // Call the method to print the introduction
		
		// The user's total score and the computer's score is less than 100
		while(userTotalScore < 100 && compTotalScore < 100){
			
			// The user's turn
			while(userTurn == true) {
				
				//  If it's the start of a roll, print out the start info
				if(userCurrentScore == 0){
					System.out.println("**** USER Turn *** \n");
					System.out.println("Your turn score:   " + userCurrentScore);
					System.out.println("Your total score:   " + userTotalScore + "\n");
				}
				
				// Asks the user to either roll or hold
				decision = Prompt.getChar("(r)oll or (h)old");
				
				// If the user enters an 'h' (user holds)
				if(decision == 'h') {
					System.out.println("You HOLD \n"); 
					
					// Add the current score to the total score
					userTotalScore += userCurrentScore; 
					
					// Print out the total score 
					System.out.println("Your total score:   " + userTotalScore + "\n");
					
					// Reset the user's current score to 0
					userCurrentScore = 0;
					
					/* If the user's total score is greater than 100
					   exit out of the user's turn */
					if (userTotalScore >= 100) {
						break;
					}

					// Move to computer's turn
					userTurn = false;
					compTurn = true;
					
				} else { 
					// If the user enters an 'r' (user holds)
					System.out.println("You ROLL \n");
					
					/* Call the method to roll and add the roll 
					   to the user's current score */
					userCurrentScore += pigDice.roll();
					
					// Call the method to print out the ASCII dice image
					pigDice.printDice();
					
					// Ends the user's turn if the dice roll equals 1 
					if(pigDice.getValue() == 1) {
						
						// Print out the total score of the user
						System.out.println("Your total score:   " + userTotalScore + "\n");
						
						// Current score is reset to zero 
						userCurrentScore = 0;
						
						// Move to the computer's turn
						userTurn = false;
						compTurn = true;
					}
					
					// Print out the current score and the total score
					System.out.println("Your turn score:   " + userCurrentScore);	
					System.out.println("Your total score:   " + userTotalScore + "\n");
				}	
					
			}
			
			// The computer's turn
			while(compTurn == true) {
				
				//  If it's the start of a roll, print out the start info
				if(compCurrentScore == 0){
					System.out.println("**** COMPUTER'S Turn ***\n");
					System.out.println("Computer's turn score:   " + compCurrentScore);
					System.out.println("Computer's total score:   " + compTotalScore + "\n");
				}
			
					// Ask user to press enter for computer to run
					System.out.println("Press enter for computer's turn");
					
					// Scanner will read the enter
					enterTurn.nextLine();
					
					// Print that the computer is rolling 
					System.out.println("Computer will ROLL");
					
					// Call the method to print out the ASCII dice image
					pigDice.printDice(); 
					
				// The current score + the next roll is less than 20
				if(compCurrentScore + pigDice.roll() < 20) {
					
					/**
					// Ask user to press enter for computer to run
					System.out.println("Press enter for computer's turn");
					// Scanner will read the enter
					enterTurn.nextLine();
					
					// Print that the computer is rolling 
					System.out.println("Computer will ROLL");
					
					*/
					
					/* Call the method to get value of the roll and 
					   add the value to the computer's current score */
					compCurrentScore += pigDice.getValue(); 
					
					// Ends the computer's turn if the dice roll equals 1 
					if(pigDice.getValue() == 1) {
						
						// Print out the computer's total score
						System.out.println("Computer's total score:   " + compTotalScore + "\n");
						
						// Current score is reset to 0
						compCurrentScore = 0;
						
						// Move to the user's turn
						userTurn = true;
						compTurn = false;
						break; 
					}	
					
					// Print out the current score and the total score
					System.out.println("Computer's turn score:   " + compCurrentScore);	
					System.out.println("Computer's total score:   " + compTotalScore + "\n");
				
				} else { 
					// The current score + the next roll is greater than 20
					
					/**
					// Ask user to press enter for computer to run
					System.out.println("Press enter for computer's turn");
					// Scanner will read the enter
					enterTurn.nextLine();
					
					// Print that the computer is rolling 
					System.out.println("Computer will ROLL");
					
					// Call the method to print out the ASCII dice image
					pigDice.printDice();
					*/
	
					System.out.println("Computer will HOLD");
					compTotalScore += compCurrentScore + pigDice.getValue();
					System.out.println("Computer's total score:   " + compTotalScore + "\n");
					compCurrentScore = 0;

					if (compTotalScore >= 100) {
						break;
					}
					userTurn = true;
					compTurn = false;
					
				}
				
			// || The computer's turn:
				// int current score 
				// int total score
			
				//** while (current score < 20)  
				
					// ask the user to enter for computer's turn ' '
					// call the method to roll [role()]
					// print out the dice face and add it to the current turn score
					
					// if roll number == 1 
						// return the total score;
						// move to user's turn;
						
				// when current score is greater than 20
					// computer will hold
					// current score will be added to total score and displayed
				
			}
		}
		
		if(userTotalScore >= 100){
			System.out.println("Congratulations!!! YOU WON!!!!");
			System.out.println("Thanks for playing the Pig Game!!!");
		} else {
			System.out.println("Womp womp rip bozo u lose");
			System.out.println("Not very demure, not very mindful");
		}
		
		enterTurn.close();
	}

	
}
