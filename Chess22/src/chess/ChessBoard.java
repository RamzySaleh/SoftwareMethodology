package chess;
import pieces.*;

public class ChessBoard {
	
	ChessPiece[][] chessBoard;
	
	public ChessBoard(){
		chessBoard = new ChessPiece[8][8];
		initializeBoard();
	}
	
	public void initializeBoard(){
		
		for(int i = 0; i < 8; i++){
			Pawn newPawn = new Pawn((i-'a')+"2");
		}
		
		
	}
	

}
