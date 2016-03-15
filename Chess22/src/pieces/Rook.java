package pieces;
/**
 * Group 22
 * @author Ramzy Saleh
 * @author Sara Zayed
 *
 */
public class Rook extends ChessPiece {
	
	public boolean firstMove;
	
	public Rook(String position, String pieceColor){
		firstMove = true;
		currentPosition = position;
		color = pieceColor;
	}
	
	/**
	 * TODO Finish implementation
	 * Only check if path is valid. DOES NOT CHECK LEAPING.
	 */
	public boolean isPathValid(String destination){
		
		return false;
	}
	
	public void setFirstMoveBoolean(boolean firstMoveValue){
		this.firstMove = firstMoveValue;
	}
	
	public String toString(){
		if (color.equals("white")) return "wR";
		return "bR";
	}
}
