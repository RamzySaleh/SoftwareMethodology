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
	
	public void movePiece(int[] origin, int[] destination){
		chessBoard[origin[0]][origin[1]] = chessBoard[destination[0]][destination[1]];
	}
	public void clear(int[] location){
		chessBoard[location[0]][location[1]] = null;
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
			newBlackPawn.setFirstMoveBoolean(true);
			newWhitePawn.setFirstMoveBoolean(true);
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
	
	public boolean isCheckDetected(String color){
		
		findKings();
		int a = (int) 'a';
		
		int letB = blackKingLocation[1]+a;
		char letterB = (char) letB;
		int mB = 8 - blackKingLocation[0];
		String numberB = Integer.toString(mB);
		String blackKing = letterB+numberB;
		
		int letW = whiteKingLocation[1]+a;
		char letterW = (char) letW;
		int mW = 8 - whiteKingLocation[0];
		String numberW = Integer.toString(mW);
		String whiteKing = letterW+numberW;
		
		/**
		 * If input is "white," this method checks if black's king is in check, and vice versa. 
		 * Change association of input color and functionality if ambiguous.
		 */

		if(color.equals("white")){
			for(int i = 0; i < 8; i++){
				for (int j = 0; j < 8; j++){
					if (chessBoard[i][j]!= null){
						//if the piece is white, & its path to the king is both valid and clear, then the opponent's king is in check
						if((chessBoard[i][j].color.equals("white")) && (chessBoard[i][j].isPathValid(blackKing)) && (isPathClear(chessBoard[i][j], blackKing))){
							return true;
						}
					}
					
				}	
			}
		}
		
		if(color.equals("black")){
			for(int i = 0; i < 8; i++){
				for (int j = 0; j < 8; j++){
					if (chessBoard[i][j]!=null){
						//if the piece is black, & its path to the king is both valid and clear, then the opponent's king is in check
						if((chessBoard[i][j].color.equals("black")) && (chessBoard[i][j].isPathValid(whiteKing)) && (isPathClear(chessBoard[i][j], whiteKing))){
							return true;
						}
					}
				}	
			}	
		}
					
		return false;
	}
	
	
	public boolean checkForCheckmate(){
		
		boolean whiteThreatened = isCheckDetected("black");
		boolean blackThreatened = isCheckDetected("white");
		
		System.out.println("whiteThreatened = "+whiteThreatened);
		System.out.println("blackThreatened = "+blackThreatened);
		findKings();
		int a = (int) 'a';
		
		if (whiteThreatened){
			
			int letW = whiteKingLocation[1]+a;
			char letterW = (char) letW;
			int mW = 8 - whiteKingLocation[0];
			String numberW = Integer.toString(mW);
			String whiteKing = letterW+numberW;
			int i,j = 10;
			int[][] possibleAttackers = new int[16][2];
			int countOfAttackers = 0;
			
			// Find all attackers
			for(i = 0; i < 8; i++){
				for (j = 0; j < 8; j++){
					if (chessBoard[i][j]!=null){
						if((chessBoard[i][j].color.equals("black")) && (chessBoard[i][j].isPathValid(whiteKing)) && (isPathClear(chessBoard[i][j], whiteKing))){
							possibleAttackers[countOfAttackers][0] = i;
							possibleAttackers[countOfAttackers][1] = j;
							countOfAttackers ++;
							System.out.println("attacker at [i][j] = ["+i+"]["+j+"]");
						}
					}
				}	
			}
			// Single check
			if (countOfAttackers == 1) {
				j = possibleAttackers[0][1];
				i = possibleAttackers[0][0];
				int letMean = j+a;
				char letterMean = (char) letMean;
				int mMean = 8 - i;
				String numberMean = Integer.toString(mMean);
				String meanGuy = letterMean+numberMean;
			
				for(i = 0; i < 8; i++){
					for (j = 0; j < 8; j++){
						if (chessBoard[i][j]!=null){
							if((chessBoard[i][j].color.equals("white")) && (chessBoard[i][j].isPathValid(meanGuy)) && (isPathClear(chessBoard[i][j], meanGuy))){
								
								// We found a piece to kill the mean guy, lets see if it gets us out of check!
								
								ChessPiece[][] tempPtr = new ChessPiece[8][8];
								ChessBoard temp = new ChessBoard();
								temp.chessBoard = tempPtr;
								
								for (int m = 0; m < 8; m ++){
									for (int n = 0; n < 8; n++){
										tempPtr[m][n] = (ChessPiece) chessBoard[m][n];
									}
								}
								
								int letSave = j+a;
								char letterSave = (char) letSave;
								int mSave = 8 - i;
								String numberSave = Integer.toString(mSave);
								String savior = letterSave+numberSave;
								
								temp.movePiece(savior, meanGuy, 'x');
								
								if (temp.isCheckDetected("black")) {
									System.out.println("Savior at failed at m,n = "+i+","+j);
									continue;
								} else {
									// We found someone to bail us out.
									System.out.println("Savior at success at m,n = "+i+","+j);
									return false;
								}
							}
						}
					}
				}
			} 
			// Double check or we cannot get anyone to save us. So, we must try to move
			
			return !tryMovingKing("white");
			
			
		} else if (blackThreatened){
			
			int letB = blackKingLocation[1]+a;
			char letterB = (char) letB;
			int mB = 8 - blackKingLocation[0];
			String numberB = Integer.toString(mB);
			String blackKing = letterB+numberB;
			int i,j = 10;
			int[][] possibleAttackers = new int[16][2];
			int countOfAttackers = 0;
			
			// Find all attackers
			for(i = 0; i < 8; i++){
				for (j = 0; j < 8; j++){
					if (chessBoard[i][j]!=null){
						if((chessBoard[i][j].color.equals("white")) && (chessBoard[i][j].isPathValid(blackKing)) && (isPathClear(chessBoard[i][j], blackKing))){
							possibleAttackers[countOfAttackers][0] = i;
							possibleAttackers[countOfAttackers][1] = j;
							countOfAttackers ++;
						}
					}
				}	
			}
			// Single check
			if (countOfAttackers == 1) {
				j = possibleAttackers[0][1];
				i = possibleAttackers[0][0];
				int letMean = j+a;
				char letterMean = (char) letMean;
				int mMean = 8 - i;
				String numberMean = Integer.toString(mMean);
				String meanGuy = letterMean+numberMean;
			
				for(i = 0; i < 8; i++){
					for (j = 0; j < 8; j++){
						if (chessBoard[i][j]!=null){
							if((chessBoard[i][j].color.equals("black")) && (chessBoard[i][j].isPathValid(meanGuy)) && (isPathClear(chessBoard[i][j], meanGuy))){
								
								// We found a piece to kill the mean guy, lets see if it gets us out of check!
								
								ChessPiece[][] tempPtr = new ChessPiece[8][8];
								ChessBoard temp = new ChessBoard();
								temp.chessBoard = tempPtr;
								
								for (int m = 0; m < 8; m ++){
									for (int n = 0; n < 8; n++){
										tempPtr[m][n] = (ChessPiece) chessBoard[m][n];
									}
								}
								
								int letSave = j+a;
								char letterSave = (char) letSave;
								int mSave = 8 - i;
								String numberSave = Integer.toString(mSave);
								String savior = letterSave+numberSave;
								
								temp.movePiece(savior, meanGuy, 'x');
								
								if (temp.isCheckDetected("white")) {
									continue;
								} else {
									// We found someone to bail us out.
									return false;
								}
							}
						}
					}
				}
			} 
			// Double check or we cannot get anyone to save us. So, we must try to move
			
			return !tryMovingKing("black");
			
			
			
		} else {
			return false;
		}
		
		
	}
	
	
	/**
	 * 
	 * @param chessPiece - piece for which we want to check if its path is clear
	 * @return If the path is clear, return true. 
	 */
	public boolean isPathClear(ChessPiece chessPiece, String destination){
		
		
		if (!chessPiece.isPathValid(destination)) return false;
		
		
		String destLetter = destination.substring(0, 1);
		String destNumber = destination.substring(1, 2);
		
		String startingLetter = chessPiece.currentPosition.substring(0, 1);
		String startingNumber = chessPiece.currentPosition.substring(1, 2);
		
		int destI = 8-Integer.parseInt(destNumber);
		int destJ = destLetter.charAt(0) - 'a';
		
		int startI = 8-Integer.parseInt(startingNumber);
		int startJ = startingLetter.charAt(0) - 'a';
		
		
		// Horizontal movement
		if (destI == startI){
			if (destJ < startJ){
				for(int j = destJ+1; j < startJ; j++){
					if(chessBoard[startI][j] != null){
						return false;
					}
				}
				// If they are all null, return true.
				return true;
			} else if (destJ > startJ){
				for(int j = startJ+1; j < destJ; j++){
					if(chessBoard[startI][j] != null){
						return false;
					}
				}
				// If they are all null, return true.
				return true;
			} else { 
				// Trying to move to the same position. Invalid move
				return false;
			}
		}
		
		// Vertical Movement
		if (destJ == startJ){
			if (destI < startI){
				for(int i = destI+1; i < startI; i++){
					if(chessBoard[i][startJ] != null){
						return false;
					}
				}
				// If they are all null, return true.
				return true;
			} else if (destI > startI){
				for(int i = startI+1; i < destI; i++){
					if(chessBoard[i][startJ] != null){
						return false;
					}
				}
				// If they are all null, return true.
				return true;
			} else { 
				// Trying to move to the same position. Invalid move
				return false;
			}
		}
		
		// Diagonal movement
		if (Math.abs(destJ-startJ) == Math.abs(destI - startI)){ // Slope is 1.
			
			if (Math.abs(destJ-startJ) == 1) return true;
			
			int j;
			
			if (destJ > startJ){
				j = startJ;
			} else {
				j = destJ;	
			}
			
			if (destI > startI){
				for(int i = startI+1; i < destI; i++){
					j++;
					if(chessBoard[i][j] != null){
						return false;
					}
				} 
			} else {
				for(int i = destI+1; i < startI; i++){
					j++;
					if(chessBoard[i][j] != null){
						return false;
					}
				} 
			}
			// If they are all null, return true.
			return true;
		}
		
		/** Path is valid, which is checked in the beginning. The movement is not
		 *  horizontal, diagonal, or vertical, so it is a knight. Leaping is
		 *  is valid so return true. 
		 */
		return true;
		
	}
	
	public boolean validityCheck(String location){
		if (location.length() != 2) return false;
		
		String requestedLetter = location.substring(0, 1);
		String requestedNumber = location.substring(1, 2);
		if(!(requestedLetter.matches("[a-zA-Z]")) || !(requestedNumber.matches("[1-8]"))){
			return false;
		}
		
		return true;
	}
	
	public ChessPiece findPieceAtLocation(String location){
		
		if (location.length() != 2) return null;
		
		String requestedLetter = location.substring(0, 1);
		String requestedNumber = location.substring(1, 2);
		
		int requestI = 8-Integer.parseInt(requestedNumber);
		int requestJ = requestedLetter.charAt(0) - 'a';
		if (requestI > 8 || requestI < 0 || requestJ > 8 || requestJ < 0) return null;
		return chessBoard[requestI][requestJ];

	}
	
	public boolean movePiece(String origin, String destination, char promotion){
		
		ChessPiece[][] tempPtr = new ChessPiece[8][8];
		ChessBoard temp = new ChessBoard();
		temp.chessBoard = tempPtr;
		
		for (int i = 0; i < 8; i++){
			for (int j = 0; j < 8; j++){
				tempPtr[i][j] = (ChessPiece) chessBoard[i][j];
			}
		}
		
		if(origin.length() != 2 || destination.length() != 2){
			return false;
		}
		
		String requestedLetter = origin.substring(0, 1);
		String requestedNumber = origin.substring(1, 2);
		
		int requestI = 8-Integer.parseInt(requestedNumber);
		int requestJ = requestedLetter.charAt(0) - 'a';
		if (requestI > 8 || requestI < 0 || requestJ > 8 || requestJ < 0) return false;
		
		String requestedLetter2 = destination.substring(0, 1);
		String requestedNumber2 = destination.substring(1, 2);
		
		int requestK = 8-Integer.parseInt(requestedNumber2);
		int requestL = requestedLetter2.charAt(0) - 'a';
		
		if (requestK > 8 || requestK < 0 || requestL > 8 || requestL < 0) return false;
		
		switch(promotion){
		case 'Q':
			Queen queenPromo = new Queen(destination, chessBoard[requestI][requestJ].color);
			tempPtr[requestK][requestL] = queenPromo;
			tempPtr[requestI][requestJ] = null;
			if (chessBoard[requestI][requestJ].color.equals("white")){
				if (temp.isCheckDetected("black")) return false;
				this.chessBoard = tempPtr;
				return true;
			} else {
				if (temp.isCheckDetected("white")) return false;
				this.chessBoard = tempPtr;
				return true;
			}
		case 'N':
			Knight knightPromo = new Knight(destination, chessBoard[requestI][requestJ].color);
			tempPtr[requestK][requestL] = knightPromo;
			tempPtr[requestI][requestJ] = null;
			if (chessBoard[requestI][requestJ].color.equals("white")){
				if (temp.isCheckDetected("black")) return false;
				this.chessBoard = tempPtr;
				return true;
			} else {
				if (temp.isCheckDetected("white")) return false;
				this.chessBoard = tempPtr;
				return true;
			}
		case 'B':
			Bishop bishopPromo = new Bishop(destination, chessBoard[requestI][requestJ].color);
			tempPtr[requestK][requestL] = bishopPromo;
			tempPtr[requestI][requestJ] = null;
			if (chessBoard[requestI][requestJ].color.equals("white")){
				if (temp.isCheckDetected("black")) return false;
				this.chessBoard = tempPtr;
				return true;
			} else {
				if (temp.isCheckDetected("white")) return false;
				this.chessBoard = tempPtr;
				return true;
			}
		case 'R':
			Rook rookPromo = new Rook(destination, chessBoard[requestI][requestJ].color);
			tempPtr[requestK][requestL] = rookPromo;
			tempPtr[requestI][requestJ] = null;
			if (chessBoard[requestI][requestJ].color.equals("white")){
				if (temp.isCheckDetected("black")) return false;
				this.chessBoard = tempPtr;
				return true;
			} else {
				if (temp.isCheckDetected("white")) return false;
				this.chessBoard = tempPtr;
				return true;
			}
		default:
			if (tempPtr[requestI][requestJ] instanceof Pawn) {
				Pawn tempPawn = (Pawn) tempPtr[requestI][requestJ];
				tempPawn.firstMove = false;
				if (Math.abs(requestJ - requestL) == 2){
					tempPawn.justMovedTwo = true;
				} else {
					tempPawn.justMovedTwo = false;
				}
				tempPtr[requestI][requestJ] = tempPawn;
			} else if (tempPtr[requestI][requestJ] instanceof King) {
				King tempKing = (King) tempPtr[requestI][requestJ];
				tempKing.firstMove = false;
				tempPtr[requestI][requestJ] = tempKing;
			} else if (tempPtr[requestI][requestJ] instanceof Rook) {
				Rook tempRook = (Rook) tempPtr[requestI][requestJ];
				tempRook.firstMove = false;
				tempPtr[requestI][requestJ] = tempRook;
			}
			tempPtr[requestK][requestL] = tempPtr[requestI][requestJ];
			tempPtr[requestK][requestL].currentPosition = destination;
			tempPtr[requestI][requestJ] = null;
			if (this.chessBoard[requestI][requestJ].color.equals("white")){
				if (temp.isCheckDetected("black")){
					System.out.println("disregarded check!");
					return false;
				}
				System.out.println("here 456");
				this.chessBoard = tempPtr;
				return true;
			} else {
				if (temp.isCheckDetected("white")) {
					System.out.println("disregarded check!");
					return false;
				}
				System.out.println("here 464");
				this.chessBoard = tempPtr;
				return true;
			}
		}	
	}
	
	public ChessPiece[][] enPassant(String origin, String destination, String toRemove){
		if(origin.length() != 2 || destination.length() != 2){
			return null;
		}
		String requestedLetter = toRemove.substring(0, 1);
		String requestedNumber = toRemove.substring(1, 2);
		
		int requestI = 8-Integer.parseInt(requestedNumber);
		int requestJ = requestedLetter.charAt(0) - 'a';
		if (requestI > 8 || requestI < 0 || requestJ > 8 || requestJ < 0) return null;
		
		chessBoard[requestI][requestJ] = null;
		movePiece(origin,destination,'x');
		
		return chessBoard;
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
	
	
	/**
	 * 
	 * @param color - color of king you are trying to save
	 * @return true - if moving King gets you out of check, false otherwise 
	 */
	public boolean tryMovingKing(String color){
		
		ChessPiece[][] tempPtr = new ChessPiece[8][8];
		ChessBoard temp = new ChessBoard();
		temp.chessBoard = tempPtr;
		
		for (int m = 0; m < 8; m++){
			for (int n = 0; m < 8; m++){
				tempPtr[m][n] = (ChessPiece) chessBoard[m][n];
			}
		}
		
		temp.findKings();
		
		if (color.equals("white")) {
			int kingI = temp.whiteKingLocation[0];
			int kingJ = temp.whiteKingLocation[1];
			int origin[] = new int[2];
			int dest[] = new int[2];
			origin[0] = kingI;
			origin[1] = kingJ;
			
			if (kingI+1 <= 7){
				if (tempPtr[kingI+1][kingJ] == null){
					dest[0] = kingI+1;
					dest[1] = kingJ;
					temp.movePiece(origin, dest);
					if(!temp.isCheckDetected("black")) return true;
				}
				if (kingJ+1 <= 7 && tempPtr[kingI+1][kingJ+1] == null){
					dest[0] = kingI+1;
					dest[1] = kingJ+1;
					temp.movePiece(origin, dest);
					if(!temp.isCheckDetected("black")) return true;
				}
				if (kingJ+1 <= 7 && tempPtr[kingI][kingJ+1] == null){
					dest[0] = kingI;
					dest[1] = kingJ+1;
					temp.movePiece(origin, dest);
					if(!temp.isCheckDetected("black")) return true;
				}
				if (kingJ-1 >= 0 && tempPtr[kingI+1][kingJ-1] == null){
					dest[0] = kingI+1;
					dest[1] = kingJ-1;
					temp.movePiece(origin, dest);
					if(!temp.isCheckDetected("black")) return true;
				}
				if (kingJ-1 >= 0 && tempPtr[kingI][kingJ-1] == null){
					dest[0] = kingI;
					dest[1] = kingJ-1;
					temp.movePiece(origin, dest);
					if(!temp.isCheckDetected("black")) return true;
				}
			} 
			if (kingI-1 >= 0){
				if (tempPtr[kingI-1][kingJ] == null){
					dest[0] = kingI-1;
					dest[1] = kingJ;
					temp.movePiece(origin, dest);
					if(!temp.isCheckDetected("black")) return true;
				}
				if (kingJ+1 <= 7 && tempPtr[kingI-1][kingJ+1] == null){
					dest[0] = kingI-1;
					dest[1] = kingJ+1;
					temp.movePiece(origin, dest);
					if(!temp.isCheckDetected("black")) return true;
				}
				if (kingJ-1 >= 0 && tempPtr[kingI-1][kingJ-1] == null){
					dest[0] = kingI-1;
					dest[1] = kingJ-1;
					temp.movePiece(origin, dest);
					if(!temp.isCheckDetected("black")) return true;
				}
			}
			
			return false;
		} else {
			int kingI = temp.blackKingLocation[0];
			int kingJ = temp.blackKingLocation[1];
			int origin[] = new int[2];
			int dest[] = new int[2];
			origin[0] = kingI;
			origin[1] = kingJ;
			
			if (kingI+1 <= 7){
				if (tempPtr[kingI+1][kingJ] == null){
					dest[0] = kingI+1;
					dest[1] = kingJ;
					temp.movePiece(origin, dest);
					if(!temp.isCheckDetected("white")) return true;
				}
				if (kingJ+1 <= 7 && tempPtr[kingI+1][kingJ+1] == null){
					dest[0] = kingI+1;
					dest[1] = kingJ+1;
					temp.movePiece(origin, dest);
					if(!temp.isCheckDetected("white")) return true;
				}
				if (kingJ+1 <= 7 && tempPtr[kingI][kingJ+1] == null){
					dest[0] = kingI;
					dest[1] = kingJ+1;
					temp.movePiece(origin, dest);
					if(!temp.isCheckDetected("white")) return true;
				}
				if (kingJ-1 >= 0 && tempPtr[kingI+1][kingJ-1] == null){
					dest[0] = kingI+1;
					dest[1] = kingJ-1;
					temp.movePiece(origin, dest);
					if(!temp.isCheckDetected("white")) return true;
				}
				if (kingJ-1 >= 0 && tempPtr[kingI][kingJ-1] == null){
					dest[0] = kingI;
					dest[1] = kingJ-1;
					temp.movePiece(origin, dest);
					if(!temp.isCheckDetected("white")) return true;
				}
			} 
			if (kingI-1 >= 0){
				if (tempPtr[kingI-1][kingJ] == null){
					dest[0] = kingI-1;
					dest[1] = kingJ;
					temp.movePiece(origin, dest);
					if(!temp.isCheckDetected("white")) return true;
				}
				if (kingJ+1 <= 7 && tempPtr[kingI-1][kingJ+1] == null){
					dest[0] = kingI-1;
					dest[1] = kingJ+1;
					temp.movePiece(origin, dest);
					if(!temp.isCheckDetected("white")) return true;
				}
				if (kingJ-1 >= 0 && tempPtr[kingI-1][kingJ-1] == null){
					dest[0] = kingI-1;
					dest[1] = kingJ-1;
					temp.movePiece(origin, dest);
					if(!temp.isCheckDetected("white")) return true;
				}
			}
			
			return false;
		}
		
		
		
	}
	
	
	

}
