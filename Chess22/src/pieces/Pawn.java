package pieces;
/**
 * Group 22
 * @author Ramzy Saleh
 * @author Sara Zayed
 *
 */
public class Pawn extends ChessPiece {
	
	public boolean firstMove;
	public boolean justMovedTwo;
	
	public Pawn(String position, String pieceColor){
		firstMove = true;
		justMovedTwo = false;
		currentPosition = position;
		color = pieceColor;
	}
	
	/**
	 * 
	 * Only check if path is valid. DOES NOT CHECK LEAPING.
	 * If user wants to move two spaces, check if it is the first move.
	 * Does not check for enpassant.
	 */
	public boolean isPathValid(String destination){
		int[] orderedPairCurrent = positionStringToArr(this.currentPosition);
		int[] orderedPair = positionStringToArr(destination);
		
		if(!coordinateValidCheck(orderedPair)){
			return false;
		}
		if(orderedPair[0] != orderedPairCurrent[0]){
			//letter is different, which means illegal diagonal movement is being made. 
			return false;
		}
		if(orderedPairCurrent[0] == orderedPair[0]){
			if(orderedPairCurrent[1] == orderedPair[1]){
				//Same spot is invalid
				System.out.println("Failed here 3");
				return false;
			}
			if(firstMove){
				if(orderedPair[1]-orderedPairCurrent[1] == -2 && color.equals("black")){
					//Pawn can jump two steps on its first move
					return true;
				} else if (orderedPair[1]-orderedPairCurrent[1] == 2 && color.equals("white")){
					return true;
				}
			}
			if(orderedPair[1]-orderedPairCurrent[1] == -1 && color.equals("black")){
				//Pawn can only move forward in single steps
				return true;
			} else if (orderedPair[1]-orderedPairCurrent[1] == 1 && color.equals("white")){
				return true;
			}
		}
		
		return false;
	}
	
	public void setFirstMoveBoolean(boolean firstMoveValue){
		this.firstMove = firstMoveValue;
	}
	
	public String toString(){
		if (color.equals("white")) return "wp";
		return "bp";
	}
	
	
}
