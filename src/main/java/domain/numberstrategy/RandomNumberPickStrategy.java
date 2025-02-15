package domain.numberstrategy;

public class RandomNumberPickStrategy implements NumberPickStrategy {

    @Override
    public int pickNumber(int min, int max) {
        return (int) (Math.random() * max) + min;
    }
}
