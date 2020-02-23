package lotto.domain;

public class LottoBall implements Comparable<LottoBall> {
    private static final int MIN_BALL_NUMBER = 0;
    private static final int MAX_BALL_NUMBER = 45;
    private static final String NUMBER_OUT_OF_RANGE_EXCEPTION = "로또 볼 범위를 벗어났습니다.";

    private int lottoNumber;

    public LottoBall(int lottoNumber) {
        if (lottoNumber < MIN_BALL_NUMBER || lottoNumber > MAX_BALL_NUMBER) {
            throw new IllegalArgumentException(NUMBER_OUT_OF_RANGE_EXCEPTION);
        }
        this.lottoNumber = lottoNumber;
    }

    public int getLottoNumber() {
        return lottoNumber;
    }

    @Override
    public int compareTo(LottoBall lottoBall) {
        return this.lottoNumber - lottoBall.lottoNumber;
    }
}
