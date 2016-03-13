package pieces;

public class Rook extends ChessPiece {
	
	public Rook(String position, String pieceColor){
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
		if (color.equals("white")) return "wR";
		return "bR";
	}
}
