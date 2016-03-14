package chess;
import pieces.*;

/**
 * Group 22
 * @author Ramzy Saleh
 * @author Sara Zayed
 *
 */

public class ChessBoard {
	
	ChessPiece[][] chessBoard;
	int[] whiteKingLocation;
	int[] blackKingLocation;
	
	public ChessBoard(){
		chessBoard = new ChessPiece[8][8];
		initializeBoard();
		whiteKingLocation = new int[2];
		blackKingLocation = new int[2];
		whiteKingLocation[0] = 7;
		whiteKingLocation[1] = 4;
		blackKingLocation[0] = 0;
		blackKingLocation[1] = 4;
		
	}
	
	/**
	 * ___________________________________________________________________
	 * Chess board array indices:
	 * 
	 * [0][0]	[0][1]	[0][2]	[0][3]	[0][4]	[0][5]	[0][6]	[0][7] 8
	 * [1][0]	[1][1]	[1][2]	[1][3]	[1][4]	[1][5]	[1][6]	[1][7] 7
	 * [2][0]	[2][1]	[2][2]	[2][3]	[2][4]	[2][5]	[2][6]	[2][7] 6
	 * [3][0]	[3][1]	[3][2]	[3][3]	[3][4]	[3][5]	[3][6]	[3][7] 5
	 * [4][0]	[4][1]	[4][2]	[4][3]	[4][4]	[4][5]	[4][6]	[4][7] 4
	 * [5][0]	[5][1]	[5][2]	[5][3]	[5][4]	[5][5]	[5][6]	[5][7] 3
	 * [6][0]	[6][1]	[6][2]	[6][3]	[6][4]	[6][5]	[6][6]	[6][7] 2
	 * [7][0]	[7][1]	[7][2]	[7][3]	[7][4]	[7][5]	[7][6]	[7][7] 1
	 *   a		  b		  c		  d		  e		  f		  g		  h
	 *   
	 * ___________________________________________________________________
	 *   Initial board:
	 *   
	 *   bR bN bB bQ bK bB bN bR 8
	 *   bp bp bp bp bp bp bp bp 7
	 *      ##    ##    ##    ## 6
	 *   ##    ##    ##    ##    5
	 *   	##    ##    ##    ## 4
	 *   ##    ##    ##    ##    3
	 *   wp wp wp wp wp wp wp wp 2
	 *   wR wN wB wQ wK wB wN wR 1
	 *    a  b  c  d  e  f  g  h
	 *    
	 * ___________________________________________________________________
	 * 
	 *   
	 */
	public void initializeBoard(){
		
		for (int i = 0; i < 8; i++){
			for (int j = 0; j < 8; j++){
				chessBoard[i][j] = null;
			}
		}
		
		for(int i = 0; i < 8; i++){	
			Pawn newBlackPawn = new Pawn(Chess.intToChar(i)+"7", "black");
			Pawn newWhitePawn = new Pawn(Chess.intToChar(i)+"2", "white");
			newBlackPawn.setFirstMove(true);
			newWhitePawn.setFirstMove(true);
			chessBoard[1][i] = newBlackPawn;
			chessBoard[6][i] = newWhitePawn;
		}
		
		Rook blackRook1 = new Rook("a8","black");
		Rook blackRook2 = new Rook("h8","black");
		Rook whiteRook1 = new Rook("a1","white");
		Rook whiteRook2 = new Rook("h1","white");
		chessBoard[0][0] = blackRook1;
		chessBoard[0][7] = blackRook2;
		chessBoard[7][0] = whiteRook1;
		chessBoard[7][7] = whiteRook2;
		
		Knight blackKnight1 = new Knight("b8","black");
		Knight blackKnight2 = new Knight("g8","black");
		Knight whiteKnight1 = new Knight("b1","white");
		Knight whiteKnight2 = new Knight("g1","white");
		chessBoard[0][1] = blackKnight1;
		chessBoard[0][6] = blackKnight2;
		chessBoard[7][1] = whiteKnight1;
		chessBoard[7][6] = whiteKnight2;
		
		Bishop blackBishop1 = new Bishop("c8","black");
		Bishop blackBishop2 = new Bishop("f8","black");
		Bishop whiteBishop1 = new Bishop("c1","white");
		Bishop whiteBishop2 = new Bishop("f1","white");
		chessBoard[0][2] = blackBishop1;
		chessBoard[0][5] = blackBishop2;
		chessBoard[7][2] = whiteBishop1;
		chessBoard[7][5] = whiteBishop2;
		
		Queen blackQueen = new Queen("d8","black");
		Queen whiteQueen = new Queen("d1","white");
		chessBoard[0][3] = blackQueen;
		chessBoard[7][3] = whiteQueen; 
		
		King blackKing = new King("e8","black");
		King whiteKing = new King("e1","white");
		chessBoard[0][4] = blackKing;
		chessBoard[7][4] = whiteKing; 
	}
	
	
	/**
	 * 
	 * Prints the chess board according to the chessBoard[][] array.
	 * 
	 * 
	 */
	public void printBoard(){
		
		String [][] printingBoard = new String[9][9];
				
		for (int i = 0; i < 8; i++) printingBoard[i][8] = Integer.toString(8-i);
		for (int i = 0; i < 8; i++) printingBoard[8][i] = " ".concat(Character.toString(Chess.intToChar(i)));
		
		printingBoard[8][8] = "  ";
		
		for (int i = 0; i < 8; i++){
			for (int j = 0; j < 8; j ++){
				
				if(chessBoard[i][j] != null) {
					printingBoard[i][j] = chessBoard[i][j].toString();
				} else {
					/** Place double number sign if 'i' is odd and 'j' is even
					 *  or if 'i' is even and 'j' is odd
					 */
					if ((i%2 == 1 && j%2 == 0) || (i%2 == 0 && j%2 == 1)){
						printingBoard[i][j] = "##";
					} else {
						printingBoard[i][j] = "  ";
					}
				}
				
			}
		}
		
		for (int i = 0; i < 9; i++){
			String currentLine = "";
			for (int j = 0; j < 9; j ++){
				if(j != 9 || j != 0) currentLine = currentLine.concat(" ");
				currentLine = currentLine.concat(printingBoard[i][j]);
			}
			System.out.println(currentLine);
		}
		
	}
	
	public boolean isCheckDetected(String lastTurn){
		
		boolean isCheckFound = false;
		
		findKings();
		
		
		return isCheckFound;
	}
	
	
	/**
	 * 
	 * @param chessPiece - piece for which we want to check if its path is clear
	 * @return If the path is clear (i.e. 
	 */
	public boolean isPathClear(ChessPiece chessPiece){
		
		boolean isPathClear = false;
		
		
		return isPathClear;
		
	}
	
	
	public void findKings(){
		for(int i = 0; i < 8; i++){
			for (int j = 0; j < 8; j++){
				if (chessBoard[i][j]!=null){
					if(chessBoard[i][j].toString().equals("bK")){
						blackKingLocation[0] = i;
						blackKingLocation[1] = j;
					} else if(chessBoard[i][j].toString().equals("wK")){
						whiteKingLocation[0] = i;
						whiteKingLocation[1] = j;
					}
				}
				
			}
			
		}
	}
	
	
	
	
	

}
