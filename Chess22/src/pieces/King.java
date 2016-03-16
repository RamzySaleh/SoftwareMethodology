package pieces;
/**
 * Group 22
 * @author Ramzy Saleh
 * @author Sara Zayed
 *
 */
public class King extends ChessPiece {
	
	public boolean firstMove;
	
	public King(String position, String pieceColor){
		firstMove = true;
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
		int same = orderedPairCurrent[1];
		int plusOne = orderedPairCurrent[1]+1;
		int minusOne = orderedPairCurrent[1]-1;
		if(!coordinateValidCheck(orderedPair)){
			return false;
		}
		//King can move one square in each direction. Therefore, letter is either +1, -1, or the same.
		//int is either +1, -1, or the same. Letter and number cannot both be the same as old coordinates.
		
		if(orderedPair[0] == orderedPairCurrent[0]+1 || orderedPair[0] == orderedPairCurrent[0]-1){
			if(orderedPair[1] == same || orderedPair[1] == plusOne || orderedPair[1] == minusOne){
				return true;
			}
		}
		else if(orderedPair[0] == orderedPairCurrent[0]){
			if(orderedPair[1] == orderedPairCurrent[1]){
				//staying in the same spot is not valid
				return false;
			}
			else if(orderedPair[1] == plusOne || orderedPair[1] == minusOne){
				return true;
			}
		}
		
		return false;
	}
	
	public void setFirstMoveBoolean(boolean firstMoveValue){
		this.firstMove = firstMoveValue;
	}
	
	public String toString(){
		if (color.equals("white")) return "wK";
		return "bK";
	}
}
