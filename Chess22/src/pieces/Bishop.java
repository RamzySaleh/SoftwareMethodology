package pieces;

/**
 * Group 22
 * @author Ramzy Saleh
 * @author Sara Zayed
 *
 */
public class Bishop extends ChessPiece {
	
	
	public Bishop(String position, String pieceColor){
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
		if (color.equals("white")) return "wB";
		return "bB";
	}
	
}
