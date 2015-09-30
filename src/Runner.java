import java.util.Scanner;

public class Runner {
	
	static int row;

	public static void main(String[] args) {
		
		Scanner s = new Scanner(System.in);
		
		double targetScore = 10.0;
		int sampleSize = 20;
		double[] scores = new double[sampleSize];
		PossiblePath[] pGroup = new PossiblePath[sampleSize];
		MineField playZone = new MineField();

		int counter = 0;
		
		double thisBestScore;
		double lastBestScore;
		
		double highScore1, highScore2;
		int hS1Index, hS2Index;
		PossiblePath best, secondBest;
		
		//initial generation of pool
		for (int i = 0; i < pGroup.length; i++) {
			pGroup[i] = new PossiblePath();
		}
		System.out.println(playZone);
		s.nextLine();
		
		//test the sequences and see how they fare!
		for (int i = 0; i < pGroup.length; i++) {
			scores[i] = playZone.testSequence(pGroup[i].getSequence(), false);
			//System.out.println(scores[i]);
		}
		
		highScore1 = scores[0];
		highScore2 = scores[1];
		hS1Index = 0;
		hS2Index = 1;
		
		//find the two best scores
		for (int i = 0; i < scores.length; i++) {
			if(scores[i] > highScore1) {
				highScore2 = highScore1;
				hS2Index = hS1Index;
				highScore1 = scores[i];
				hS1Index = i;
			} else if(scores[i] > highScore2) {
				highScore2 = scores[i];
				hS2Index = i;
			}
		}
		
		System.out.println("\nHigh Scores:");
		System.out.println(highScore1);
		System.out.println(highScore2);
		best = pGroup[hS1Index];
		secondBest = pGroup[hS2Index];
		playZone.testSequence(best.getSequence(), true);
		thisBestScore = highScore1;
		
		while(row != 0) { //until the path reaches the top row
			//initial generation of pool
			if(counter < 100) {
			    for (int i = 0; i < pGroup.length; i++) {
				    pGroup[i] = new PossiblePath(best, secondBest);
			    }
			} else {
				playZone.testSequence(best.getSequence(), true);
				for (int i = 0; i < pGroup.length; i++) {
					pGroup[i] = new PossiblePath();
				}
			}
			
			//test the sequences and see how they fare!
			for (int i = 0; i < pGroup.length; i++) {
				scores[i] = playZone.testSequence(pGroup[i].getSequence(), false);
				//System.out.println(scores[i]);
			}
			
			highScore1 = scores[0];
			highScore2 = scores[1];
			hS1Index = 0;
			hS2Index = 1;
			
			//find the two best scores
			for (int i = 0; i < scores.length; i++) {
				if(scores[i] > highScore1) {
					highScore2 = highScore1;
					hS2Index = hS1Index;
					highScore1 = scores[i];
					hS1Index = i;
				} else if(scores[i] > highScore2) {
					highScore2 = scores[i];
					hS2Index = i;
				}
			}
			
			System.out.println("\nHigh Scores:");
			System.out.println(highScore1);
			System.out.println(highScore2);
			best = pGroup[hS1Index];
			secondBest = pGroup[hS2Index];
			
			
			lastBestScore = thisBestScore;
			thisBestScore = highScore1;
			if(lastBestScore == thisBestScore) {
			    counter++;
			} else {
				counter = 0;
			}
			playZone.testSequence(best.getSequence(), true);
		}
	}
	
	public static void readCurrentRow(int currentRow){
		row = currentRow;
	}

}
