package lotto.model;

public class Yield {
    private static final int GAIN_THRESHOLD = 1;

    private final float yield;

    public Yield(float yield) {
        this.yield = yield;
    }

    public static Yield calculate(LottoMoney lottoMoney, Long totalWinningMoney) {
        return new Yield(lottoMoney.divide(totalWinningMoney));
    }

    public boolean isGain() {
        return yield >= GAIN_THRESHOLD;
    }

    public float getYield() {
        return yield;
    }
}
