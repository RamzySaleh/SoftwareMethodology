package pieces;
/**
 * Group 22
 * @author Ramzy Saleh
 * @author Sara Zayed
 *
 */
public class Queen extends ChessPiece {
	
	public Queen(String position, String pieceColor){
		currentPosition = position;
		color = pieceColor;
	}
	
	/**
	 * 
	 * Only check if path is valid. DOES NOT CHECK LEAPING.
	 */
	public boolean isPathValid(String destination){
		
		int[] orderedPairCurrent = positionStringToArr(this.currentPosition);
		int[] orderedPair = positionStringToArr(destination);
		
		if(orderedPairCurrent[0] == orderedPair[0]){
			if(orderedPairCurrent[1] == orderedPair[1]){
				//Same spot is invalid
				return false;
			}
		}
		
		if(coordinateValidCheck(orderedPair)){
			//NEED TO REDO IMPLEMENTATION
		}
		return false;
	}
	public String toString(){
		if (color.equals("white")) return "wQ";
		return "bQ";
	}
}
