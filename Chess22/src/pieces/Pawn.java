package pieces;

public class Pawn extends ChessPiece {
	
	boolean firstMove;
	
	public Pawn(String position){
		firstMove = true;
		currentPosition = position;
	}
	
	public boolean isMoveValid(String destination){
		
		return false;
	}
	
	public void setFirstMove (boolean firstMoveValue){
		this.firstMove = firstMoveValue;
	}
	
	
}
