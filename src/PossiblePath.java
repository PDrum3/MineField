import java.util.Random;

public class PossiblePath {

    private String sequence;
    public static final int SEQUENCE_LENGTH = 50;

    public PossiblePath() {
        sequence = PossiblePath.getRandomSequence();
    }

    public PossiblePath(PossiblePath chosen1, PossiblePath chosen2) {
        sequence = PossiblePath.getOffspringSequence(chosen1,chosen2);
    }
    
    static public String getOffspringSequence(PossiblePath str1, PossiblePath str2) {
    	Random r = new Random();
    	int generated;
    	String result = "";
    	for(int i = 0; i < SEQUENCE_LENGTH; i++) {
    	    generated = r.nextInt(15);
    	    if(generated < 7) {
    	    	result += str1.getDirection(i);
    	    }else if(generated < 14) {
    	    	result += str1.getDirection(i);
    	    }else if(generated == 14) {
    	    	result += getCode();
    	    }
    	}
    	return result;
    }
    
    static public String getRandomSequence() {

        String result = "";
        for (int i = 0; i < SEQUENCE_LENGTH; i++) {
            result += getCode();
        }
        return result;
    }

    static public String getCode() {
        Random gen = new Random();
        int code = gen.nextInt(4);
        if (code == 0) {
            return "u";
        } else if (code == 1) {
            return "d";
        } else if (code == 2) {
            return "l";
        } else {
            return "r";
        }
    }

    public String getSequence() {
        return sequence;
    }

    public String getDirection(int index) {
        return sequence.substring(index, index +1);
    }
    
    public String toString() {
    	return "The sequence is " + sequence;
    }


}
