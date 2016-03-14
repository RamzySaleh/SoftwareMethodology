package pieces;
/**
 * Group 22
 * @author Ramzy Saleh
 * @author Sara Zayed
 *
 */
public class King extends ChessPiece {
	
	
	public King(String position, String pieceColor){
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
	
	public String toString(){
		if (color.equals("white")) return "wK";
		return "bK";
	}
}
