/**
 * Menu class that contains the main method
 * and other methods to process user's input.
 * @author Gerda Ugne Pupelyte
 */
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {

	private Lottery lottery;
	
	/**
	 * Default constructor for the class Menu
	 */
	public Menu() {
		// TODO Auto-generated constructor stub
		lottery = new Lottery();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Menu menu = new Menu();
		menu.processUsersChoices();
	}
	
	/**
	 * Method for displaying available menu options to the user.
	 */
	public void displayMenu()
	{
		System.out.println("Please choose one of the following options:");
		System.out.println("1. Run the lottery");
		System.out.println("2. Run the lottery over the course of multiple weeks");
		System.out.println("3. Set a valid range of lottery numbers");
		System.out.println("4. Change the default number amount per ticket");
		System.out.println("0. Exit");
	}
	
	/**
	 * Method that processes user's choices.
	 * Depending on user's input, appropriate methods are called.
	 * 
	 * 1 - Runs the lottery
	 * 2 - Runs the lottery over the course of multiple weeks
	 * 3 - Sets a valid range of lottery numbers
	 * 0 - Exits the system
	 */
	public void processUsersChoices()
	{
		String choice;
		
		do
		{
			//Menu options displayed
			displayMenu();
			
			//user's input is read
			Scanner s = new Scanner(System.in);			
			choice = s.nextLine();
			
			//Corresponding menu option is executed
			if (choice.equals("1"))
			{
				int winnings = 0;
				
				//Old user numbers are cleared
				lottery.getUserNumbers().clear();
				
				//New user numbers are set
				lottery.setUserNumbers();
				
				//New random lottery numbers are set
				lottery.setRandomNumbers();
				
				//Matches are calcuated
				lottery.calculateIntersection();
				
				System.out.println("\nYour numbers:");
				System.out.println(lottery.getUserNumbers());
				
				System.out.println("\nLottery numbers:");
				System.out.println(lottery.getLotteryNumbers());
					
				winnings = lottery.calculateWinnings();
				//Outcome is printed depending on win/loss
				if(winnings > 0)
				{
					System.out.println("\nCongratulations, you won! Your winnings: "+ "£" + winnings);
				}
				else
				{
					System.out.println("\nYou lost! Your lost:" + " £" + winnings*-1);
				}
				
			}
			else if ((choice.equals("2")))
			{
				Scanner s2 = new Scanner(System.in);
				int input;
				try
				{
					System.out.println("\nPlease enter the amount of weeks you want to run the lottery for:");
					input = s2.nextInt();
					
					//Old user numbers are cleared
					lottery.getUserNumbers().clear();
					
					//New user numbers are set
					lottery.setUserNumbers();
					
					//New random lottery numbers are set
					lottery.setRandomNumbers();
					
					int totalWinnings = 0;
					
					System.out.println("\nYour numbers:");
					System.out.println(lottery.getUserNumbers());
					
					//Loops repeats for the week count
					for(int i=0; i<input; i++)
					{
						//Each time new lottery numbers are generated
						lottery.setRandomNumbers();
						
						//Intersection is recalculated
						lottery.calculateIntersection();

						System.out.println("\nLottery numbers for week " + (i+1) + ":");
						System.out.println(lottery.getLotteryNumbers());
						
						//Total winnings are summed up
						totalWinnings = totalWinnings + lottery.calculateWinnings();
						
					}
					
					System.out.println("\nYou have spent £" + Lottery.getTicketPrice() * input + " on the tickets.");
					
					if(totalWinnings >= 0)
					{
						System.out.println("You won! You have earned £" + totalWinnings + " in total, including the ticket prices.\n");
					}
					else
					{
						
						System.out.println("You lost! You have lost £" + -1*totalWinnings + ", earning nothing.");
					
					}
				}
				catch (InputMismatchException e)
				{
					System.out.println("Please check your input.");
				}
			}
			//Changes the range of lottery numbers
			else if (choice.equals("3"))
			{
				System.out.println("Set the range of lottery numbers:");
				
				Scanner s3 = new Scanner(System.in);
				int range;
				try
				{
					range = s.nextInt();
					lottery.setLottery_max(range);
					System.out.println("Range changed successfully!\n");
					
				}
				catch (InputMismatchException e)
				{
					System.out.println("Wrong value.Please try again.\n");
				}
				
			}
			//Changes the default number amount per ticket
			else if(choice.equals("4"))
			{
				System.out.println("Choose a new default number amount per ticket:");
				Scanner s3 = new Scanner(System.in);
				int amount;
				try
				{
					amount = s.nextInt();
					lottery.setAmount_of_numbers(amount);
					System.out.println("Default amount of numbers changed successfully!\n");
					
				}
				catch (InputMismatchException e)
				{
					System.out.println("Wrong value.Please try again.\n");
				}
			}
			// Exits the system
			else if (choice.equals("0"))
			{
				System.out.println("Goodbye.");
				System.exit(0);
			}
			else
			{
				System.out.println("Please check your input and try again.");
			}
			
		} while(!(choice.equals("0")));

	}

}
