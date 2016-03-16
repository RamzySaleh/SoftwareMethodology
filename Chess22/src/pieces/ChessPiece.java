package pieces;
/**
 * Group 22
 * @author Ramzy Saleh
 * @author Sara Zayed
 *
 */
public class ChessPiece {
	
	public String currentPosition;
	public String color;
	
	public boolean isPathValid(String destination){
		return false;
	}
	public void setCurrentPosition(String position){
		currentPosition = position;
	}
	public void setColor(String color){
		this.color = color;
	}
	public boolean coordinateValidCheck(int[] orderedPair){
		
		if(orderedPair[0] < 1 || orderedPair[0] > 8 || orderedPair[1] < 1 || orderedPair[1] > 8){
			return false;
		}
		return true;
	}
	public int[] positionStringToArr(String position){		
		int[] orderedPair = new int[2];		
		orderedPair[0] = -1;		
		orderedPair[1] = -1;		
		 			
		position = position.toLowerCase();		
		 			
		if (position.length() != 2) return orderedPair;		
		 			
		int letterToInt = position.charAt(0)-'a'+1;		
		orderedPair[0] = letterToInt;		
		orderedPair[1] = (int)position.charAt(1);		
		 			
		return orderedPair;				
	}

	
}
