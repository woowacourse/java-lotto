package lotto.model;

public class Yield {
    private static final int GAIN_THRESHOLD = 1;

    private final float yield;

    public Yield(float yield) {
        this.yield = yield;
    }

    public boolean isGain() {
        return yield >= GAIN_THRESHOLD;
    }

    public float getYield() {
        return yield;
    }
}
