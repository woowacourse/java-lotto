package lotto.domain;

public class LottoBall {
    private int lottoBall;

    public LottoBall(int lottoBall){
        if(lottoBall<0 || lottoBall > 45){
            throw new IllegalArgumentException("로또 볼 범위를 벗어났습니다.");
        }
        this.lottoBall = lottoBall;
    }
}
