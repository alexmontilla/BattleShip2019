import java.util.Scanner; 

public class BattleShip {
	
	public static final int SHIP_SYMBOL = 'S';
	public static final int WATER_SYMBOL = 'O';
	public static final int SUNK_SHIP_SYMBOL = 'X';
	public static final int EMPTY_SYMBOL = '.';
	
	public static final int NUM_SHIPS = 10;
	public static final int DIMENSION = 8;	
	static char[][] matrix = new char[DIMENSION][DIMENSION];
	static boolean gameOver;
	static int ships = 10;
	public static int shots = 10;
	static char letter;
	static int number;
	
	public static void main(String[] args) {
		

		Scanner input = new Scanner(System.in);
			     	     	
		gameOver = false;
	    initMatrix();
	    addShipsToMatrix();
	    	     
	    while(!gameOver) {
	    	printMatrix(false);
	    	askCoordinates(input);
	    	shoot(letter, number);
	    	checkGameOver();
	    	
	    }
	     showResult();
	}






	private static void askCoordinates(Scanner input) {
		letter = 'º';
		while (!inRange(letter))	{
			System.out.println("Enter row (Letter): ");
			letter = input.next().charAt(0);
		}
		System.out.println("Enter column (Number): ");
		number = input.nextInt();
	}
	

		
		
	

	private static boolean inRange(char letter2) {
		if (letter < 'A' || letter > 'A' + DIMENSION - 1)	{
			return false;
		} else {
			return true;
		}
	}






	private static void showResult() {
		if (ships == 0)	{
			System.out.println("You WIN!!!");
		} else {
			System.out.println("You don't have more shoots :( ");
		}
		
	}






	private static void checkGameOver() {
		if (ships > 0 || shots > 0)	{
			gameOver = true;
		}
		
	}






	private static void shoot(char letter, int number) {
		
		int row = letter - 'A';
		int col = number - 1;
		
		if(ships > 0)	{
			if (matrix[row][col] == SHIP_SYMBOL)	{
			matrix[row][col] = SUNK_SHIP_SYMBOL;		
			ships--;
			} else {
				if (matrix[row][col] != SUNK_SHIP_SYMBOL)	{
					matrix[row][col] = WATER_SYMBOL;
				}
					
			}
			shots--;
		}
	}

	private static void addShipsToMatrix() {
		
		long shipCounter = 0;
		int randomRow, randomCol;
		
		while (shipCounter < NUM_SHIPS) {
			randomRow = (int) (Math.random() * DIMENSION);
			randomCol= (int) (Math.random() * DIMENSION);
	
			if (matrix[randomRow][randomCol] != SHIP_SYMBOL) {
				matrix[randomRow][randomCol] = SHIP_SYMBOL;
				shipCounter ++;
			}
		}
		
	}

	public static void initMatrix() {
		for (int row = 0; row < matrix.length; row++) {
			for (int col = 0; col < matrix[0].length; col++) {
				matrix[row][col] = EMPTY_SYMBOL;				
			}
		}
	}
	
	public static void printMatrix(boolean debug) {
		printHeader();
		char c = 'A';
		for (int row = 0; row < matrix.length; row++) {
			System.out.print(c + " ");
			c++;
			for (int col = 0; col < matrix[0].length; col++) {
				if (matrix[row][col] == SHIP_SYMBOL) {
					if (debug) {
						System.out.print(matrix[row][col] + " ");
					} else {
						System.out.print(Character.toString(EMPTY_SYMBOL)
								+ " ");
					}
				} else {
					System.out.print(matrix[row][col] + " ");
				}			
			}
			System.out.println();
		}
	}

	private static void printHeader() {
		System.out.print("  ");
		for(int i = 1; i <= matrix[0].length; i++ ) {
			System.out.print(i + " ");
		}
		System.out.println();
		
	}

}
