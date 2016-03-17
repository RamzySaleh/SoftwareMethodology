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
		if(!coordinateValidCheck(orderedPair)){
			return false;
		}
		if(orderedPairCurrent[0] == orderedPair[0]){
			if(orderedPairCurrent[1] == orderedPair[1]){
				//Same spot is invalid
				return false;
			}
			//letter is the same, number is different: forward/backward movement, which is valid
			else return true;
		}
		if(orderedPairCurrent[1] == orderedPair[1]){
			//Already did a check for the same spot, and for same letter
			//letter is different, number is the same: side to side movement 
			return true;	
			}
		else if((Math.abs(orderedPair[0]-orderedPairCurrent[0])) == (Math.abs(orderedPair[1]-orderedPairCurrent[1]))){
			//diagonal movement
			return true;
		}
		
		return false;
	}
	public String toString(){
		if (color.equals("white")) return "wQ";
		return "bQ";
	}
}
