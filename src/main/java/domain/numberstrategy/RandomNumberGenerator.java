package domain.numberstrategy;

public class RandomNumberGenerator implements NumberGenerator {

    private final int min;
    private final int max;

    public RandomNumberGenerator(int min, int max) {
        this.min = min;
        this.max = max;
    }

    @Override
    public int generate() {
        return (int) (Math.random() * max) + min;
    }
}
