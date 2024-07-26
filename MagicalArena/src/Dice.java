import java.util.Random;

public class Dice {
    private static final Random random = new Random();

    public int roll() {
        return random.nextInt(6) + 1; 
    }
}