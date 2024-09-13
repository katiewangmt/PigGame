/**
 *	The game of Pig.
 *	(Description here)
 *
 *	@author	
 *	@since	
 */
public class PigGame {
	
	public static void main(String[] args) {
		
		printIntroduction();
		
		// || The User's turn:
		// Variables:
			// int current score 
			// int total score
		
		// ask the user to either roll or hold (use the promptTester to get the char)
		
			// **if the char is equal to r 
				// call the method to roll [role()] <- return the number you rolled
				// print out the dice face and the current turn score
			
				// ask the user to either roll or hold (use the promptTester to get the char)
						
					// if the user holds
						// add current turn score to the total score
						// move to computer's turn
					
					// if the user rolls
						
		
			
			// **if the char is equal to h
				// call the method to roll [role()]
		
		
		// || The computer's turn:
		
		
		
	}
	
	
	
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
	
}
