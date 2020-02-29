/**
 * Lottery class contains user/lottery numbers and appropriate methods
 * to set these values and calculate the intersections between these two sets
 * 
 * @author Gerda Ugne Pupelyte
 */
import java.util.HashSet;
import java.util.Set;
import java.util.Random;
import java.util.Scanner;

public class Lottery {
	
	private Set<Integer> userNumbers;
	private Set<Integer> lotteryNumbers;
	
	private Set<Integer> intersection;
	private int lottery_max = 10;
	private int amount_of_numbers = 6;
	private final static int TICKET_PRICE = 2;
	

	/**
	 * Default constructor for Lottery class
	 */
	public Lottery() {
		
		// TODO Auto-generated constructor stub
		userNumbers = new HashSet<Integer>();
		lotteryNumbers = new HashSet<Integer>();	
		
		setRandomNumbers();
		//Intersection is originally a copy of set a
		intersection = new HashSet<Integer>(userNumbers);

		
	}

	public Set<Integer> getUserNumbers() {
		return userNumbers;
	}

	public int getLottery_max() {
		return lottery_max;
	}

	public void setLottery_max(int lottery_max) {
		this.lottery_max = lottery_max;
	}

	public int getAmount_of_numbers() {
		return amount_of_numbers;
	}

	public Set<Integer> getLotteryNumbers() {
		return lotteryNumbers;
	}

	public void setLotteryNumbers(Set<Integer> lotteryNumbers) {
		this.lotteryNumbers = lotteryNumbers;
	}
	
	
	/**
	 * Calculates the intersection of lottery and user numbers
	 * @return true if the list has changed as a result of the call
	 */
	public boolean calculateIntersection()
	{
		//User number set is copied
		intersection = new HashSet<Integer>(userNumbers);
		
		return intersection.retainAll(lotteryNumbers);
	}
	
	public Set<Integer> getIntersection()
	{
		return intersection;
	}
	
	/**
	 * Sets random lottery numbers between 1 and determined max lottery range
	 */
	public void setRandomNumbers()
	{
		Random rand = new Random();
		int num = rand.nextInt(lottery_max+1);
		boolean retry = false;
		
		do
		{
			//Old numbers are deleted
			lotteryNumbers.clear();
			
			for(int i=0; i<amount_of_numbers; i++)
			{
				lotteryNumbers.add(num);
				
				num = rand.nextInt(lottery_max);
			}
			
			//If there were duplicates, numbers are regenerated
			if(!(lotteryNumbers.size() == amount_of_numbers))
			{
				retry = true;
				continue;
			}
			
			retry = false;
			
		}while(retry==true);
	}
	
	/**
	 * Calculates winnings, which depend on the numbers matched
	 * @return int - amount of money won 
	 */
	public int calculateWinnings()
	{
		int matches = intersection.size();
		
		//Each number corresponds to the money won
		switch(matches)
		{
		case 3: return 25-TICKET_PRICE;
		case 4: return 100 - TICKET_PRICE;
		case 5: return 1000-TICKET_PRICE;
		case 6: return 1000000 - TICKET_PRICE;
		default: return 0 - TICKET_PRICE;
		}
		
	}

	public void setAmount_of_numbers(int amount_of_numbers) {
		this.amount_of_numbers = amount_of_numbers;
	}

	public static int getTicketPrice() {
		return TICKET_PRICE;
	}

	/**
	 * Sets user's numbers according to user's input.
	 * 
	 */
	public void setUserNumbers()
	{
		Scanner s = new Scanner(System.in);
		String userInput;
		int number;
		
		boolean retry = false;
		
		do
		{
			retry = false;
			
			System.out.println("Please enter your chosen " + amount_of_numbers + " lottery numbers, seperated by a space:");
			userNumbers.clear();
			userInput = s.nextLine();
			
			//Input is split
			String [] splitString = userInput.split(" ");
			
			//If initially the input has too little/too many numbers user is prompted to re-enter
			if(!(splitString.length == amount_of_numbers))
			{
				System.out.println("Your input is incorrect. Please try again!"); 
				retry = true; 
				continue;
			}
			
			for (int i=0; i<splitString.length;i++)
			{			
				try
				{
					if(isNumeric(splitString[i]) == true)
					{
						// If the value is a number, it's added to the set
						number = Integer.parseInt(splitString[i]);
						
						//If the value is out of range limits, user is prompted to re-enter
						if(number>=1 && number <= lottery_max)
						{
							userNumbers.add(number);
							retry = false;
						}
						else
						{
							System.out.println("The number you've entered is out of range limits.");
							retry = true;
							break;
						}

					}
					else
					{
						System.out.println("Invalid input! Please try again");
							
						//Returns true to prompt to reenter input
						retry = true;
						break;
					}
					}
				catch (NumberFormatException e)
				{
					System.out.println("An error has occured. Please try again");
					//Returns true to prompt to reenter input
					retry = true;
					break;
					
				}
			}
			
			//System checks if any numbers are duplicate and prompts to re-enter
			if(!(userNumbers.size() == amount_of_numbers))
			{
				System.out.println("Check your input for duplicate numbers and invalid characters. Please try again!"); 
				retry = true; 
				continue;
				
			}
			
		} while(retry==true);

	
	}
	

	
	/**
	 * Method that checks whether the String value is a number.
	 * @param value - String that is being checked
	 * @return false/true depending on outcome of the test
	 */
	public boolean isNumeric(String value)
	{
		int test = 0;
		
		try
		{	//The system tries to convert String into int
			test = Integer.parseInt(value);
			return true;
		}
		catch (NumberFormatException e)
		{
			// In case of an error we know it was not an number
			return false;
		}
	}

}
