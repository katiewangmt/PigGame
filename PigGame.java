/**
 *	The game of Pig.
 *	(Description here)
 *
 *	@author	
 *	@since	
 */
import java.util.Scanner;
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
		PigGame p = new PigGame();
		Scanner temp = new Scanner(System.in); 
		Boolean userTurn = true;
		Boolean compTurn = false;
		// || The User's turn:
		int userCurrentScore = 0;
		int userTotalScore = 0;
		int compCurrentScore = 0;
		int compTotalScore = 0;
		char decision;
		
		Dice pigDice = new Dice();
		p.printIntroduction();

		while(userTotalScore < 100 && compTotalScore < 100){
		
			while(userTurn == true) {
				
				if(userCurrentScore == 0){
					System.out.println("**** USER Turn *** \n");
					System.out.println("Your turn score:   " + userCurrentScore);
					System.out.println("Your total score:   " + userTotalScore + "\n");
				}
				
				// ask the user to either roll or hold (use the promptTester to get the char)
				decision = Prompt.getChar("(r)oll or (h)old");
				
				// **the char is equal to h
				if(decision == 'h') {
					System.out.println("You HOLD \n"); 
					userTotalScore += userCurrentScore;
					System.out.println("Your total score:   " + userTotalScore + "\n"); //add it to the total score
					userCurrentScore = 0;

					if (userTotalScore >= 100) {
						break;
					}

					// move to computer's turn
					userTurn = false;
					compTurn = true;
				} else { 
					// **while the char is equal to r 
					System.out.println("You ROLL \n"); 
					userCurrentScore += pigDice.roll(); // call the method to roll [role()] <- return the number you rolled
					pigDice.printDice(); // print out the dice face 
					
					if(pigDice.getValue() == 1) {
						System.out.println("Your total score:   " + userTotalScore + "\n");
						userCurrentScore = 0;
						userTurn = false;
						compTurn = true;
					}
				
					System.out.println("Your turn score:   " + userCurrentScore);	
					System.out.println("Your total score:   " + userTotalScore + "\n");
				}	
					
			}
			
			
			while(compTurn == true) {
				if(compCurrentScore == 0){
					System.out.println("**** COMPUTER'S Turn ***\n");
					System.out.println("Computer's turn score:   " + compCurrentScore);
					System.out.println("Computer's total score:   " + compTotalScore + "\n");
				}
			
				if(compCurrentScore + pigDice.roll() < 20) {
					System.out.println("Press enter for computer's turn");
					temp.nextLine();
					System.out.println("Computer will ROLL");
					compCurrentScore += pigDice.getValue(); // call the method to roll [roll()] <- return the number you rolled
					pigDice.printDice(); // print out the dice face 
					
					if(pigDice.getValue() == 1) {
						System.out.println("COMPUTER BOZO ROLLED A 1 LOL");
						System.out.println("Computer's total score:   " + compTotalScore + "\n");
						compCurrentScore = 0;
						userTurn = true;
						compTurn = false;
						break; 
					}	
				
					System.out.println("Computer's turn score:   " + compCurrentScore);	
					System.out.println("Computer's total score:   " + compTotalScore + "\n");
				
				} else {
					
					System.out.println("Press enter for computer's turn");
					temp.nextLine(); 
					
					System.out.println("Computer will ROLL");
					pigDice.printDice(); // print out the dice face 
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

	}

	

}
	
	
