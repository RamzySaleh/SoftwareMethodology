package pieces;

public class Pawn extends ChessPiece {
	
	boolean firstMove;
	
	public Pawn(String position, String pieceColor){
		firstMove = true;
		currentPosition = position;
		color = pieceColor;
	}
	
	/**
	 * TODO Finish implementation
	 * Only check if path is valid. DOES NOT CHECK LEAPING.
	 * If user wants to move two spaces, check if it is the first move.
	 */
	public boolean isPathValid(String destination){
		
		return false;
	}
	
	public void setFirstMove (boolean firstMoveValue){
		this.firstMove = firstMoveValue;
	}
	
	public String toString(){
		if (color.equals("white")) return "wp";
		return "bp";
	}
	
	
}
