package pieces;

public class ChessPiece {
	
	String currentPosition;
	
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
	
	
	public boolean isMoveValid(String destination){
		return false;
	}
}
