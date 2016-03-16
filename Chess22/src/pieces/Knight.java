package pieces;
/**
 * Group 22
 * @author Ramzy Saleh
 * @author Sara Zayed
 *
 */
public class Knight extends ChessPiece {
	
	public Knight(String position, String pieceColor){
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
		}
		//The knight moves in the shape of an L. There are eight valid moves for a knight.
		
		if(orderedPair[0] == orderedPairCurrent[0]+2){
			if((orderedPair[1] == orderedPairCurrent[1]+1) || (orderedPair[1] == orderedPairCurrent[1]-1)){
				//two to the left, one forward or backward
				return true;
			}
			
		}
		if(orderedPair[0] == orderedPairCurrent[0]-2){
			if((orderedPair[1] == orderedPairCurrent[1]+1) || (orderedPair[1] == orderedPairCurrent[1]-1)){
				//two to the left, one forward or backward
				return true;
			}
			
		}
		if(orderedPair[0] == orderedPairCurrent[0]+1){
			if((orderedPair[1] == orderedPairCurrent[1]+2) || (orderedPair[1] == orderedPairCurrent[1]-2)){
				//two forward or backward, one to the right
				return true;
			}
			
		}
		if(orderedPair[0] == orderedPairCurrent[0]-1){
			if((orderedPair[1] == orderedPairCurrent[1]+2) || (orderedPair[1] == orderedPairCurrent[1]-2)){
				//two forward or backward, one to the left
				return true;
			}
			
		}
		
		return false;
	}
	public String toString(){
		if (color.equals("white")) return "wN";
		return "bN";
	}
}
