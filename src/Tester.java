/**
 * Tester class to test methods of Lottery class.
 * @author Gerda Ugne Pupelyte
 *
 */

public class Tester {
	
	Lottery lottery;

	public Tester() {
		// TODO Auto-generated constructor stub
		lottery = new Lottery();
	}

	
	/**
	 * Prints user, lottery numbers and the intersection
	 */
	public void printSet()
	{

		System.out.println("\nUser numbers:");
		System.out.println(lottery.getUserNumbers());
		
		System.out.println("\nLottery numbers:");
		System.out.println(lottery.getLotteryNumbers());
		
		System.out.println("\nIntersection:");
		System.out.println(lottery.getIntersection());
	}
	
	/**
	 * Runs tests:
	 * Sets user numbers, calculates intersection and displays winnings;
	 * repeats test for 20 weeks. 
	 */
	public void test()
	{
		lottery.setUserNumbers();
		System.out.println("\nIntersection calculated:");
		lottery.calculateIntersection();
		printSet();
		
		System.out.println("\nWinnings:");
		System.out.println(lottery.calculateWinnings());
		
		for(int i=0; i<20; i++)
		{
			lottery.setRandomNumbers();
			System.out.println("\nIntersection calculated:");
			lottery.calculateIntersection();
			printSet();
			
			System.out.println("\nWinnings:");
			System.out.println(lottery.calculateWinnings());

		}
	}
	
	
}
