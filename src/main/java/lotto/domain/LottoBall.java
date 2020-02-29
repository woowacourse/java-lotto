package lotto.domain;

public class LottoBall {
    private static final int MIN_LOTTO_BALL = 1;
    private static final int MAX_LOTTO_BALL = 45;
    private final int lottoBall;

    public LottoBall(int lottoBall) {
        validateLottoBallRange(lottoBall);

        this.lottoBall = lottoBall;
    }

    private void validateLottoBallRange(int lottoBall){
        if(lottoBall< MIN_LOTTO_BALL || lottoBall > MAX_LOTTO_BALL){
            throw new IllegalArgumentException("로또 볼의 범위를 벗어났습니다.");
        }
    }

    public int getLottoBall() {
        return this.lottoBall;
    }
}
