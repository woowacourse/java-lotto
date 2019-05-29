package lotto.domain;

public class LottoNumber extends Number {
    private LottoNumber(int lottoNumber) {
        super(lottoNumber);
    }

    public static LottoNumber of(int lottoNumber) {
        return new LottoNumber(lottoNumber);
    }
}
