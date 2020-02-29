package lotto.domain;

import lotto.utils.ValidationUtils;

public class LottoBall {

    private final int lottoBall;

    public LottoBall(int lottoBall) {
        validateLottoBallRange(lottoBall);

        this.lottoBall = lottoBall;
    }

    private void validateLottoBallRange(int lottoBall) {
        ValidationUtils.validateLottoBallOutOfRange(lottoBall);
    }

    public int getLottoBall() {
        return this.lottoBall;
    }
}
