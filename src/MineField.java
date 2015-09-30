import java.util.Random;

public class MineField {
	static final int BOARD_SIZE = 30;
    int[][] field = new int[BOARD_SIZE][BOARD_SIZE];
    final int SAFE_SPACE_CHANCE = 5;


    public MineField() {
    	placeMines();
    }
    
    public void placeMines(){
    	Random r = new Random();
    	int generated;
    	for(int i = 0; i < field.length; i++) {
    		for(int j = 0; j < field[i].length; j++){
    			generated = r.nextInt(SAFE_SPACE_CHANCE);
    			if(generated == 0) {
    				field[i][j] = 1;
    			} else {
    				field[i][j] = 0;
    			}
    		}
    		field[BOARD_SIZE-1][(BOARD_SIZE/2)-1] = 0; //so the starting space isn't overwritten
    	}
    }
    
    public double testSequence(String sequenceToTest, boolean printResults) {
    	int[][] testField = makeTestArray();
    	int currentRow = BOARD_SIZE-1;
    	int currentCol = (BOARD_SIZE/2)-1;
    	testField[currentRow][currentCol] = 2;
    	int movesAllowed = 0;
    	int downMoves = 0;
    	for(int i = 0; i < sequenceToTest.length(); i++) {
    		
    		
    		if(sequenceToTest.charAt(i) == 'u'){ //go up
    			if(currentRow - 1 > -1) { //the spot you try to go to exists on the board
    				if(testField[currentRow-1][currentCol] != 1 && testField[currentRow-1][currentCol] != 2) { //there isn't a mine on the spot you want to go to and you haven't gone there before
    					currentRow--;
    					testField[currentRow][currentCol] = 2; //mark the spot on the board where you were
    				} else {
    					break;
    				}
    			} else {
    				break;
    			}
    		}
    		
    		if(sequenceToTest.charAt(i) == 'd'){ //go down
    			if(currentRow + 1 < BOARD_SIZE) { //the spot you try to go to exists on the board
    				if(testField[currentRow+1][currentCol] != 1 && testField[currentRow+1][currentCol] != 2) { //there isn't a mine on the spot you want to go to
    					currentRow++;
    					testField[currentRow][currentCol] = 2; //mark the spot on the board where you are (for fun)
    					downMoves++;
    				} else {
    					break;
    				}
    			} else {
    				break;
    			}
    		}
    		
    		if(sequenceToTest.charAt(i) == 'l'){ //go left
    			if(currentCol - 1 > -1) { //the spot you try to go to exists on the board
    				if(testField[currentRow][currentCol-1] != 1 && testField[currentRow][currentCol-1] != 2) { //there isn't a mine on the spot you want to go to
    					currentCol--;
    					testField[currentRow][currentCol] = 2; //mark the spot on the board where you are (for fun)
    				} else {
    					break;
    				}
    			} else {
    				break;
    			}
    		}
    		
    		if(sequenceToTest.charAt(i) == 'r'){ //go right
    			if(currentCol + 1 < BOARD_SIZE) { //the spot you try to go to exists on the board
    				if(testField[currentRow][currentCol+1] != 1 && testField[currentRow][currentCol+1] != 2) { //there isn't a mine on the spot you want to go to
    					currentCol++;
    					testField[currentRow][currentCol] = 2; //mark the spot on the board where you are (for fun)
    				} else {
    					break;
    				}
    			} else {
    				break;
    			}
    		}
    		movesAllowed++;
    		
    	}
    	
    	if(printResults) {
    		printArray(testField, sequenceToTest);
    	}
    	
    	testField[currentRow][currentCol] = 2;
    	double score = BOARD_SIZE - currentRow + (movesAllowed * .5) + downMoves;
    	Runner.readCurrentRow(currentRow);
    	return score;
    }
    
    private int[][] makeTestArray() {
    	int[][] result = new int[BOARD_SIZE][BOARD_SIZE];
    	for(int i = 0; i < field.length; i++) {
    		for(int j = 0; j < field[i].length; j++){
                result[i][j] = field[i][j];
    		}
    	}
    	
    	return result;
    }
    
    public String toString() {
    	String result = "";
    	for(int i = 0; i < field.length; i++) {
    		for(int j = 0; j < field[i].length; j++){
                result += field[i][j] + " ";
    		}
    		result += "\n";
    	}
    	return result;
    }
    
    public void printArray(int[][] field, String sequence) {
    	String result = "";
    	for(int i = 0; i < field.length; i++) {
    		for(int j = 0; j < field[i].length; j++){
                result += field[i][j] + " ";
    		}
    		result += "\n";
    	}
    	System.out.println(result);
    	System.out.println(sequence);
    }
    
}

