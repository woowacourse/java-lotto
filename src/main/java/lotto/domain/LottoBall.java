package lotto.domain;

import lotto.utils.ValidationUtils;

public class LottoBall implements Comparable<LottoBall> {

    private final int lottoBall;

    public LottoBall(String lottoBall) {
        ValidationUtils.validateIntegerNumberFormat(lottoBall);
        ValidationUtils.validatePositiveNumber(lottoBall);
        ValidationUtils.validateLottoBallOutOfRange(lottoBall);

        this.lottoBall = Integer.parseInt(lottoBall);
    }

    public int getLottoBall() {
        return this.lottoBall;
    }

    @Override
    public int compareTo(LottoBall o) {
        return this.lottoBall - o.getLottoBall();
    }
}
