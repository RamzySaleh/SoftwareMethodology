package chess;
/**
 * Group 22
 * @author Ramzy Saleh
 * @author Sara Zayed
 *
 */
import java.util.Scanner;

import pieces.*;

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
			
			chessBoard.printBoard();
			System.out.println("");
			System.out.print(turn);
			String requestedMove = scanner.nextLine();
			
			
			// Check if the user is requesting a draw.
			// If there was no draw requested, then saying 'draw' is an illegal move.
			// If there was a draw requested, then the game ends in a draw.
			// If there is a resign, the game ends. 
			if (drawRequested && requestedMove.equals("draw")){
				drawAccepted = true; 
				break;
			} else if (!drawRequested && requestedMove.equals("draw")){
				System.out.println("Illegal move, try again");
				continue;
			} else if (requestedMove.equals("resign")){
				resign = true;
				if(turn.equals("White's move:")){
					winner = "Black wins";
				} else {
					winner = "White wins";
				}
				break;
			}
			
			// Split the move based on spaces.
			String[] requestedMoveArr = requestedMove.split("\\s+");
			
			// We should now check if the array is of size the right size.
			// It should be of length 2 or 3 - ex: "a5 e3 N" (promotion) or "a5 e3". We checked the cases with one input,
			// which is 'draw' or 'resign'. 
			if(requestedMoveArr.length != 2 || requestedMoveArr.length != 3){
				System.out.println("Illegal move, try again");
				continue;
			}
			
			// Find the piece the user wants to move.
			ChessPiece movingPiece = chessBoard.findPieceAtLocation(requestedMoveArr[0]);
			String destination = requestedMoveArr[1];
			
			// Either the user is trying to move a piece that is not in the
			// requested spot, or the entered piece location is not of the right 
			// form. (Examples include "ee1ee" or "t3" or "113232", etc.)
			if (movingPiece == null) {
				System.out.println("Illegal move, try again");
				continue;
			}
			
			
			// Check if the user is moving their piece
			if(turn.equals("White's move:")){
				if (movingPiece.color.equals("black")) {
					System.out.println("Illegal move, try again");
					continue;
				}
			} else {
				if (movingPiece.color.equals("white")) {
					System.out.println("Illegal move, try again");
					continue;
				}
			}
			
			// Checks if the piece can travel along this path and
			// whether the path is clear.
			if (!movingPiece.isPathValid(destination) || !chessBoard.isPathClear(movingPiece, destination)){
				
				// If it isn't a Pawn, we know right away that it is illegal.
				// We will now check for enpassant in the else statement.
				if(!(movingPiece instanceof Pawn)){
					System.out.println("Illegal move, try again");
					continue;
				} else {
					
					// Cannot perform enpassant, if there is nothing in the destination.
					if(chessBoard.findPieceAtLocation(destination) == null){
						System.out.println("Illegal move, try again");
						continue;
					}
					
					ChessPiece destPiece = chessBoard.findPieceAtLocation(destination);
					
					int[] movingOrderedPair = movingPiece.positionStringToArr(movingPiece.currentPosition);
					int[] destinOrderedPair = destPiece.positionStringToArr(destination);
					
					// If the Pawn is not a direct diagonal, then it is an illegal move.
					if(Math.abs(movingOrderedPair[0]-destinOrderedPair[0])!=1 ||
							Math.abs(movingOrderedPair[1]-destinOrderedPair[1])!=1){
						System.out.println("Illegal move, try again");
						continue;
					}
					
				}
			}
			
			
			/**
			 * TODO Finish implementation
			 * 
			 */
			if (requestedMoveArr.length == 3 && requestedMoveArr[2].equals("draw")){
				drawRequested = true;
			} else if (requestedMoveArr.length == 3 && requestedMoveArr[2].equals("Q")){
				
			} else if (requestedMoveArr.length == 3 && requestedMoveArr[2].equals("N")){
				
			} else if (requestedMoveArr.length == 3 && requestedMoveArr[2].equals("K")){
				
			} else if (requestedMoveArr.length == 3 && requestedMoveArr[2].equals("B")){
				
			} else {
				System.out.println("Illegal move, try again");
				continue;
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
