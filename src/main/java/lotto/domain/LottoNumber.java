package lotto.domain;

public class LottoNumber {

    private static final String LOTTO_NUMBER_RANGE_EXCEPTION_MESSAGE = "로또 번호는 1부터 45까지입니다.";
    private static final int MIN_LOTTO_NUMBER = 1;
    private static final int MAX_LOTTO_NUMBER = 45;
    private int lottoNumber;

    public LottoNumber(int lottoNumber) {
        checkLottoNumberRange(lottoNumber);
        this.lottoNumber = lottoNumber;
    }

    private void checkLottoNumberRange(int lottoNumber) {
        if (lottoNumber < MIN_LOTTO_NUMBER || lottoNumber > MAX_LOTTO_NUMBER) {
            throw new IllegalArgumentException(LOTTO_NUMBER_RANGE_EXCEPTION_MESSAGE);
        }
    }

    public int getNumber() {
        return this.lottoNumber;
    }
}
