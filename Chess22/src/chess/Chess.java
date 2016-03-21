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

	
	public static void main(String[] args) {
		
		ChessBoard chessBoard = new ChessBoard();

		Boolean drawRequested = false;
		Boolean drawAccepted = false;
		Boolean legalMove = false;
		String turn = "White's move:";
		String winner = "";
		Boolean resign = false;
		Boolean isCheckDetected = false;
		Boolean checkMate = false;
		Boolean staleMate = false;
		Scanner scanner = new Scanner (System.in);
		
		while (!drawAccepted && !resign && !checkMate && !staleMate) {

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
			
			
			System.out.println("");
			chessBoard.printBoard();
			System.out.println("");
			if(isCheckDetected){ 
				if (chessBoard.checkForCheckmate()){
					checkMate = true;
					break;
				}
				System.out.println("Check");
			}
			System.out.print(turn);
			String requestedMove = scanner.nextLine();
			
			
			/** Check if the user is requesting a draw.
			* If there was no draw requested, then saying 'draw' is an illegal move.
			* If there was a draw requested, then the game ends in a draw.
			* If there is a resign, the game ends.
			*/ 
			if (drawRequested && requestedMove.equals("draw")){
				drawAccepted = true; 
				break;
			} else if (!drawRequested && requestedMove.equals("draw")){
				System.out.println("Illegal move, try again");
				legalMove = false;
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
			
			/** We should now check if the array is of size the right size.
			* It should be of length 2 or 3 - ex: "a5 e3 N" (promotion) or "a5 e3". We checked the cases with one input,
			* which is 'draw' or 'resign'. 
			*/
			if(requestedMoveArr.length != 2){
				if(requestedMoveArr.length != 3){
					System.out.println("Error 1 Illegal move, try again");
					legalMove = false;
					continue;
				}
			}
			
			// Find the piece the user wants to move.
			ChessPiece movingPiece = chessBoard.findPieceAtLocation(requestedMoveArr[0]);
			String destination = requestedMoveArr[1];
			
			/** Either the user is trying to move a piece that is not in the
			* requested spot, or the entered piece location is not of the right 
			* form. (Examples include "ee1ee" or "t3" or "113232", etc.)
			*/
			if (movingPiece == null) {
				System.out.println("Error 2 Illegal move, try again");
				legalMove = false;
				continue;
			}
			
			
			// Check if the user is moving their piece
			if(turn.equals("White's move:")){
				if (movingPiece.color.equals("black")) {
					System.out.println("Error 6 Illegal move, try again");
					legalMove = false;
					continue;
				}
			} else {
				if (movingPiece.color.equals("white")) {
					System.out.println("Error 7 Illegal move, try again");
					legalMove = false;
					continue;
				}
			}
			
			// Checks if the piece can travel along this path and whether the path is clear.
			if (!movingPiece.isPathValid(destination) || !chessBoard.isPathClear(movingPiece, destination)){
				/**
				 * CASTLING
				 */
				if((movingPiece instanceof King)){
					King tempKingCheck = (King) movingPiece;
					if(tempKingCheck.firstMove != true){
						System.out.println("Error a jillion Illegal move, try again");
						continue;
					}
					if(movingPiece.color.equals("white")){
						switch(destination){
						case "g1":
							if((chessBoard.findPieceAtLocation("f1") != null) || (chessBoard.findPieceAtLocation("g1") != null) ||(chessBoard.findPieceAtLocation("h1") == null)){
								System.out.println("Illegal move, try again");
								continue;
							}
							if((chessBoard.movePiece("e1", "f1", 'x') == false) || (chessBoard.movePiece("e1", "g1", 'x') == false)){
								System.out.println("Illegal move, try again");
								continue;
							}
							Rook tempRookCheck = (Rook) chessBoard.findPieceAtLocation("h1");
							if(tempRookCheck.firstMove != true){
								System.out.println("Illegal move, try again");
								continue;
							}
							else{
								chessBoard.movePiece("e1", destination, 'x');
								chessBoard.movePiece("h1", "f1", 'x');
							}
						case "c1":
							if((chessBoard.findPieceAtLocation("d1") != null) || (chessBoard.findPieceAtLocation("c1") != null) || (chessBoard.findPieceAtLocation("b1") != null) || (chessBoard.findPieceAtLocation("a1") == null)){
								System.out.println("Illegal move, try again");
								continue;
							}
							if((chessBoard.movePiece("e1", "d1", 'x') == false) || (chessBoard.movePiece("e1", "c1", 'x') == false)){
								System.out.println("Illegal move, try again");
								continue;
							}
							Rook tempRookCheck2 = (Rook) chessBoard.findPieceAtLocation("a1");
							if(tempRookCheck2.firstMove != true){
								System.out.println("Illegal move, try again");
								continue;
							}
							else{
								chessBoard.movePiece("e1", destination, 'x');
								chessBoard.movePiece("a1", "d1", 'x');
							}
						default:
							System.out.println("Error a quadrillion Illegal move, try again");
							continue;
						}
					}
					else{
						switch(destination){
						case "g8":
							if((chessBoard.findPieceAtLocation("f8") != null) || (chessBoard.findPieceAtLocation("g8") != null) ||(chessBoard.findPieceAtLocation("h8") == null)){
								System.out.println("Illegal move, try again");
								continue;
							}
							if((chessBoard.movePiece("e8", "f8", 'x') == false) || (chessBoard.movePiece("e8", "g8", 'x') == false)){
								System.out.println("Illegal move, try again");
								continue;
							}
							Rook tempRookCheck = (Rook) chessBoard.findPieceAtLocation("h8");
							if(tempRookCheck.firstMove != true){
								System.out.println("Illegal move, try again");
								continue;
							}
							else{
								chessBoard.movePiece("e8", destination, 'x');
								chessBoard.movePiece("h8", "f8", 'x');
							}
						case "c8":
							if((chessBoard.findPieceAtLocation("d8") != null) || (chessBoard.findPieceAtLocation("c8") != null) || (chessBoard.findPieceAtLocation("b8") != null) || (chessBoard.findPieceAtLocation("a8") == null)){
								System.out.println("Illegal move, try again");
								continue;
							}
							if((chessBoard.movePiece("e8", "d8", 'x') == false) || (chessBoard.movePiece("e8", "c8", 'x') == false)){
								System.out.println("Illegal move, try again");
								continue;
							}
							Rook tempRookCheck2 = (Rook) chessBoard.findPieceAtLocation("a8");
							if(tempRookCheck2.firstMove != true){
								System.out.println("Illegal move, try again");
								continue;
							}
							else{
								chessBoard.movePiece("e8", destination, 'x');
								chessBoard.movePiece("a8", "d8", 'x');
							}
						default:
							System.out.println("Illegal move, try again");
							continue;
						}
					}	
				}
				// If it isn't a Pawn or a King, we know right away that it is illegal.
				if(!(movingPiece instanceof Pawn)){
					System.out.println("Error 3 Illegal move, try again");
					legalMove = false;
					continue;
				} else {
					
					if(chessBoard.findPieceAtLocation(destination) == null){
						
						int test;
						if(movingPiece.color.equals("white")){
							test = (Character.getNumericValue(destination.charAt(1)))-1;
						}
						else{
							test = (Character.getNumericValue(destination.charAt(1)))+1;
						}
						
						String newDest = destination.charAt(0)+(Integer.toString(test));

						if((chessBoard.findPieceAtLocation(newDest) != null) && ((chessBoard.findPieceAtLocation(newDest) instanceof Pawn))){
							Pawn tempPawn = (Pawn) (chessBoard.findPieceAtLocation(newDest));
							if(tempPawn.justMovedTwo == true){
								chessBoard.enPassant(requestedMoveArr[0], destination, newDest);
								continue;
							}
						}
						
						System.out.println("Error 4 Illegal move, try again");
						legalMove = false;
						continue;
					}
					
					ChessPiece destPiece = chessBoard.findPieceAtLocation(destination);
					
					int[] movingOrderedPair = movingPiece.positionStringToArr(movingPiece.currentPosition);
					int[] destinOrderedPair = destPiece.positionStringToArr(destination);
					
					// If the Pawn is not a direct diagonal, then it is an illegal move.
					if(Math.abs(movingOrderedPair[0]-destinOrderedPair[0])!=1 ||
							Math.abs(movingOrderedPair[1]-destinOrderedPair[1])!=1){
						System.out.println("Illegal move 600, try again");
						legalMove = false;
						continue;
					}
					
				}
			}
			// Ensure that player cannot capture member of their own team
			if(turn.equals("White's move:")){
				if((chessBoard.findPieceAtLocation(destination) != null) && (chessBoard.findPieceAtLocation(destination)).color.equals("white")){
					System.out.println("Error 20 Illegal move, try again");
					legalMove = false;
					continue;
				}
			} else{
				if(((chessBoard.findPieceAtLocation(destination) != null) && (chessBoard.findPieceAtLocation(destination)).color.equals("black"))){
					System.out.println("Error 21 Illegal move, try again");
					legalMove = false;
					continue;
				}	
			}
			/**
			 * IMPLICIT PROMOTION (pawn is promoted to queen without explicit choice)
			 */
			if((movingPiece instanceof Pawn) && (destination.charAt(1) == '8' || destination.charAt(1) == '1') && (requestedMoveArr.length != 3)){
				if(!chessBoard.movePiece(requestedMoveArr[0], requestedMoveArr[1], 'Q')) System.out.println("Moved without regard to check, Illegal move, try again");
				legalMove = false;
				continue;
			}
			
			/**
			 * EXPLICIT PROMOTION (player chooses desired promotion)
			 */
			if(requestedMoveArr.length == 3){
				if(requestedMoveArr[2].equals("draw?")){
					drawRequested = true;
				} // Checking to see if the pawn actually reached the end of the board 
				else if((movingPiece instanceof Pawn) && (destination.charAt(1) == '8' || destination.charAt(1) == '1')){
					switch(requestedMoveArr[2]){
					case "Q":
						boolean returnVal = chessBoard.movePiece(requestedMoveArr[0], requestedMoveArr[1], 'Q');
						if(!returnVal) System.out.println("Moved without regard to check, Illegal move, try again");
						continue;
					case "N":
						if(!chessBoard.movePiece(requestedMoveArr[0], requestedMoveArr[1], 'N')) System.out.println("Moved without regard to check, Illegal move, try again");
						continue;
					case "K":
						if(!chessBoard.movePiece(requestedMoveArr[0], requestedMoveArr[1], 'K')) System.out.println("Moved without regard to check, Illegal move, try again");
						continue;
					case "B":
						if(!chessBoard.movePiece(requestedMoveArr[0], requestedMoveArr[1], 'B')) System.out.println("Moved without regard to check, Illegal move, try again");
						continue;
					case "R":
						if(!chessBoard.movePiece(requestedMoveArr[0], requestedMoveArr[1], 'R')) System.out.println("Moved without regard to check, Illegal move, try again");
						continue;
					default:
						System.out.println("Illegal move, try again");
						continue;
					}
				}
				else {
					System.out.println("Illegal move, try again");
					continue;
				}
			}
			legalMove = true;
			if (legalMove) {
				boolean returnVal = chessBoard.movePiece(requestedMoveArr[0], requestedMoveArr[1], 'x');
				if(!returnVal){ 
					System.out.println("Moved without regard to check, Illegal move, try again");
					continue;
				}
				if(turn.equals("White's move:")){
					if(chessBoard.isCheckDetected("white")){
						isCheckDetected = true;
					} else {
						isCheckDetected = false;
					}
					turn = "Black's move:";
				} else {
					if(chessBoard.isCheckDetected("black")){
						isCheckDetected = true;
					} else {
						isCheckDetected = false;
					}
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
