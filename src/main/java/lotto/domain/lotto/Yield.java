package lotto.domain.lotto;

public class Yield {

    private static double YIELD_MIN = 0;

    private final double rate;

    public Yield(double rate) {
        validate(rate);
        this.rate = rate;
    }

    public Yield(Yield yield) {
        this.rate = yield.rate;
    }

    void validate(double yield) {
        if (yield < YIELD_MIN) {
            throw new IllegalArgumentException("존재할 수 없는 수익률입니다.");
        }
    }

    public double unwrap() {
        return rate;
    }
}
