import java.util.Arrays;
import java.util.Scanner;

public class Board {
	private char[][] data;
	private char player;
	private char inactivePlayer;
	
	public Board() {
		char[][] board = new char[6][7];
		for (int row = 0; row < 6; row++) {
			for (int col = 0; col < 7; col++) {
				board[row][col] = ' ';
			}
		}
		data = board;
		player = 'X';
		inactivePlayer = 'O';
	}

	public char currentPlayer() {
		return player;
	}


	public boolean play(int column) {
		if (gameOver())
			throw new RuntimeException();
		column = column -1;
		if (column > 6 || column < 0)
			return false;
		if (data[5][column] == ' ')
			data[5][column] = currentPlayer();
		else if (data[4][column] == ' ')
			data[4][column] = currentPlayer();
		else if (data[3][column] == ' ')
			data[3][column] = currentPlayer();
		else if (data[2][column] == ' ')
			data[2][column] = currentPlayer();
		else if (data[1][column] == ' ')
			data[1][column] = currentPlayer();
		else if (data[0][column] == ' ')
			data[0][column] = currentPlayer();
		else
			return false;
		
		if (player == 'X') {
			player = 'O';
			inactivePlayer = 'X';
		}
		else {
			player = 'X';
			inactivePlayer = 'O';
		}
		return true;
	}

	private boolean vertical() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 7; j++) { 
				if ((data[i][j] == inactivePlayer) && (data[i+1][j] == inactivePlayer) && (data[i+2][j] == inactivePlayer) && (data[i+3][j] == inactivePlayer)) return true;
			}
		}
		return false;
	}
	
	private boolean horizontal() {
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 4; j++) { 
				if ((data[i][j] == inactivePlayer) && (data[i][j+1] == inactivePlayer) && (data[i][j+2] == inactivePlayer) && (data[i][j+3] == inactivePlayer)) return true;
			}
		}
		return false;
	}
	
	private boolean diagonal() {
		//up
		for (int i = 3; i < 6; i++) {
			for (int j = 0; j < 4; j++) { 
				if ((data[i][j] == inactivePlayer) && (data[i-1][j+1] == inactivePlayer) && (data[i-2][j+2] == inactivePlayer) && (data[i-3][j+3] == inactivePlayer)) return true;
			}
		}
		//down
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 4; j++) { 
				if ((data[i][j] == inactivePlayer) && (data[i+1][j+1] == inactivePlayer) && (data[i+2][j+2] == inactivePlayer) && (data[i+3][j+3] == inactivePlayer)) return true;
			}
		}
		return false;
	}
	
	private boolean full() {
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 7; j++) { 
		if (data[i][j] == ' ') return false;
			}
		}
		return true;
	}

	public boolean gameOver() {
		if (vertical()) 
			return true;
		else if (horizontal())
			return true;
		else if (diagonal())
			return true;
		else if (full())
			return true;
		else return false;
	}

	public char winner() {
		if (full()) return ' ';
		else return inactivePlayer;
	}

	public String toString() {
		for (char[] row : data)
		{
		    System.out.println(Arrays.toString(row));
		}
		return "";
	}

	public static void main(String[] args) {
		Board b = new Board();
		while (!b.gameOver()) {
			boolean legalMove = false;
			while (!legalMove) {
				System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n");
				System.out.println(b);
				System.out.println("Current player: " + b.currentPlayer());
				System.out.println("Enter column number for next move: ");
				Scanner in = new Scanner(System.in);
				int col = in.nextInt();
				legalMove = b.play(col);
			}
		}
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n");
		System.out.println(b);
		System.out.println("GAME OVER");
		if (b.winner() == ' ')
			System.out.println("It's a draw");
		else
			System.out.println(b.winner() + " WINS!!!");			
	}
}
