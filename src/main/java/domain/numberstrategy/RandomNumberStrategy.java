package domain.numberstrategy;

public class RandomNumberStrategy implements NumberStrategy {

    @Override
    public int pickNumber(int maxNumber) {
        return (int) (Math.random() * maxNumber) + 1;
    }
}
