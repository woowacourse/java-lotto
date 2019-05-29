package lotto.domain;

import lotto.exceptions.LottoNumberException;

public class LottoNumber extends Number {
    private static final String INVALID_LOTTO_NUMBER = "로또 번호의 범위는 1-45 입니다.";

    private LottoNumber(int lottoNumber) {
        super(lottoNumber);
    }

    public static LottoNumber is(int lottoNumber) {
        return new LottoNumber(lottoNumber);
    }

    @Override
    public void valid(int lottoNumber) {
        if (lottoNumber < 0 || lottoNumber > 45) {
            throw new LottoNumberException(INVALID_LOTTO_NUMBER);
        }
    }
}
