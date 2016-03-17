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
	 * 
	 * Only check if path is valid. DOES NOT CHECK LEAPING.
	 */
	public boolean isPathValid(String destination){
		//The bishop may move as far as it wants, but only diagonally.
		int[] orderedPairCurrent = positionStringToArr(this.currentPosition);
		int[] orderedPair = positionStringToArr(destination);
		if(!coordinateValidCheck(orderedPair)){
			return false;
		}
		//Both number and letter need to change for the move to be diagonal.
		//This check ensures not only that the destination is not the same spot, but also
		//that the move is not a forward, backward, or side to side move.
		if((orderedPair[0] == orderedPairCurrent[0]) || (orderedPair[1] == orderedPairCurrent[1])){
			return false;
		}
		else if((Math.abs(orderedPair[0]-orderedPairCurrent[0])) == (Math.abs(orderedPair[1]-orderedPairCurrent[1]))){
			return true;
		}
		
		return false;
	}
	
	public String toString(){
		if (color.equals("white")) return "wB";
		return "bB";
	}
	
}
