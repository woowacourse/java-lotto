package lotto.domain;

public class LottoNumber {
    public static final int MINIMUM_NUMBER = 1;
    public static final int MAXIMUM_NUMBER = 45;
    private static final String ERROR_LOTTO_NUMBER = "로또 숫자가 아닙니다";

    private final int lottoNumber;

    public LottoNumber(int lottoNumber) {
        validateLottoNumber(lottoNumber);
        this.lottoNumber = lottoNumber;
    }

    private void validateLottoNumber(final int lottoNumber) {
        if (lottoNumber < MINIMUM_NUMBER || lottoNumber > MAXIMUM_NUMBER) {
            throw new IllegalArgumentException(ERROR_LOTTO_NUMBER);
        }
    }

    public int getLottoNumber() {
        return lottoNumber;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        return this.lottoNumber == ((LottoNumber)obj).lottoNumber;
    }
}
