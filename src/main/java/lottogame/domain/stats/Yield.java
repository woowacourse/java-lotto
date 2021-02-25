package lottogame.domain.stats;

public class Yield {
    private final float yield;

    public Yield(int totalWinningAmount, Money money) {
        yield = (float) totalWinningAmount / money.getMoney();
    }

    public float value() {
        return yield;
    }
}
