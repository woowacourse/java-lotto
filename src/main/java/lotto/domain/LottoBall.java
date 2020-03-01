package lotto.domain;

import lotto.utils.StringUtils;
import lotto.utils.ValidationUtils;

public class LottoBall implements Comparable<LottoBall> {

    private final int lottoBall;

    public LottoBall(String lottoBall) {
        validateLottoBall(lottoBall);

        this.lottoBall = StringUtils.stringToInt(lottoBall);
    }

    private void validateLottoBall(String lottoBall) {
        ValidationUtils.validateIntegerNumberFormat(lottoBall);
        ValidationUtils.validatePositiveNumber(lottoBall);
        ValidationUtils.validateLottoBallOutOfRange(lottoBall);
    }

    public int getLottoBall() {
        return this.lottoBall;
    }

    @Override
    public int compareTo(LottoBall o) {
        return this.lottoBall - o.getLottoBall();
    }
}
