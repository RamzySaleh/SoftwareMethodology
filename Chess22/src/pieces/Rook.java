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
	 * 
	 * Only check if path is valid. DOES NOT CHECK LEAPING.
	 */
	public boolean isPathValid(String destination){
		//The rook may move as far as it wants, but only forward, backward, and to the sides.
		//Therefore, diagonal movements are not allowed.
		int[] orderedPairCurrent = positionStringToArr(this.currentPosition);
		int[] orderedPair = positionStringToArr(destination);
		if(!coordinateValidCheck(orderedPair)){
			return false;
		}
		if(orderedPairCurrent[0] == orderedPair[0]){
			if(orderedPairCurrent[1] == orderedPair[1]){
				//Same spot is invalid
				return false;
			}
			//letter is the same, number is different: forward or backward movement
			else return true;
		}
		if(orderedPairCurrent[1] == orderedPair[1]){
		//Already did a check for the same spot, and for same letter
		//letter is different, number is the same: side to side movement 
		return true;	
		}
		
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
