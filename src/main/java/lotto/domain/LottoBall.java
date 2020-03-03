package lotto.domain;

import lotto.utils.StringUtils;

public class LottoBall implements Comparable<LottoBall> {
    private static final int MIN_LOTTO_BALL = 1;
    private static final int MAX_LOTTO_BALL = 45;
    private static final String OUT_OF_RANGE_LOTTO_BALL = "로또 볼의 범위를 벗어났습니다.";

    private final int lottoBall;

    public LottoBall(String lottoBall) {
        validateLottoBall(lottoBall);

        this.lottoBall = StringUtils.stringToInt(lottoBall);
    }

    private void validateLottoBall(String lottoBall) {
        if (Integer.parseInt(lottoBall) < MIN_LOTTO_BALL || Integer.parseInt(lottoBall) > MAX_LOTTO_BALL) {
            throw new IllegalArgumentException(OUT_OF_RANGE_LOTTO_BALL);
        }
    }

    public int getLottoBall() {
        return this.lottoBall;
    }

    @Override
    public int compareTo(LottoBall o) {
        return this.lottoBall - o.getLottoBall();
    }
}
