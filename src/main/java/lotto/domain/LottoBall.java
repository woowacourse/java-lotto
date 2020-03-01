package lotto.domain;

import lotto.utils.ValidationUtils;

public class LottoBall implements Comparable<LottoBall> {

    private final int lottoBall;

    public LottoBall(int lottoBall) {
        ValidationUtils.validateLottoBallOutOfRange(lottoBall);

        this.lottoBall = lottoBall;
    }

    public int getLottoBall() {
        return this.lottoBall;
    }

    @Override
    public int compareTo(LottoBall o) {
        return this.lottoBall - o.getLottoBall();
    }
}
