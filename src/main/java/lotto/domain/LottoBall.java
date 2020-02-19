package lotto.domain;

public class LottoBall implements Comparable<LottoBall> {
    private int lottoNumber;

    public LottoBall(int lottoNumber){
        if(lottoNumber < 0 || lottoNumber > 45){
            throw new IllegalArgumentException("로또 볼 범위를 벗어났습니다.");
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

    @Override
    public boolean equals(Object obj) {
        LottoBall lottoBall = (LottoBall)obj;
        return lottoNumber == lottoBall.getLottoNumber();
    }

    @Override
    public int hashCode() {
        return super.hashCode()+137;
    }
}
