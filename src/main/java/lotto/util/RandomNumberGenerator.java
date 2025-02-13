package lotto.util;

public class RandomNumberGenerator implements NumberGenerator {
    @Override
    public int generate(int minimum, int maximum) {
        return (int) (Math.random() * maximum) + minimum;
    }
}
