package chess;
/**
 * Group 22
 * @author Ramzy Saleh
 * @author Sara Zayed
 *
 */
import java.util.Scanner;

public class Chess {

	/**
	 * TODO Finish implementation
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ChessBoard chessBoard = new ChessBoard();
		chessBoard.printBoard();
		
		Boolean drawRequested = false;
		Boolean drawAccepted = false;
		Boolean legalMove = false;
		String turn = "White's move:";
		String winner = "";
		Boolean resign = false;
		Boolean checkMate = false;
		Boolean staleMate = false;
		
		System.out.println("");
		
		Scanner scanner = new Scanner (System.in);
		
		while (!drawAccepted && !resign && !checkMate && staleMate) {
			
			System.out.print(turn);
			String requestedMove = scanner.nextLine();
			
			if (drawRequested && requestedMove.equals("draw")){
				drawAccepted = true; 
				break;
			}
				
			/** 
			 *  Split user input by space. 
			 * 	Task 1: Check if they are moving their piece
			 *  Task 2: Check if that piece can move to the destination. This includes:
			 *  	 1. Check if the path to the destination involves no leaping (except for Knight)
			 *  	 2. (Implemented by each piece's class) Check if piece is allowed to move in such pattern (i.e. rook moving horizontally or vertically, etc.)
			 *  		2a. If a pawn is capturing, ignore the false return of the Pawn isPathValid.
			 *       3. Moving such piece does not put their own King under attack
			 *  	 4. If there is a 'Check', the move must place the King out of harms way.
			 *  		4a. By capturing checking piece.
			 *          4b. King itself is moved out of harms way.
			 *          4c. Placing piece in path of checking piece (valid for Queen, Rook, and Bishop ONLY)
			 *          4d. Castling is NOT valid in time of 'Check'
			 *         	4e. If a-c cannot occur, then it is a CHECKMATE!
			 *       5. If there is no 'Check', but a player has no valid moves, it is a STALEMATE!
			 *         		
			*/
			
			if (legalMove) {
				if(turn.equals("White's move:")){
					turn = "Black's move:";
				} else {
					turn = "White's move:";
				}
			}

			legalMove = false;
		}
		
		if (drawAccepted || staleMate) { 
			System.out.println("Draw");
		} else {
			System.out.println(winner);
		}
		scanner.close();
		
		
	}
	
	public static char intToChar(int i){
		return (char) (i+97);
	}

}
