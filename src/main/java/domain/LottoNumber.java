package domain;

public class LottoNumber {
    private static final int MIN_LOTTO_NUMBER = 1;
    private static final int MAX_LOTTO_NUMBER = 45;

    private int lottoNumber;

    public LottoNumber(int lottoNumber) {
        validateLottoNumberRange(lottoNumber);
        this.lottoNumber = lottoNumber;
    }

    private void validateLottoNumberRange(int lottoNumber) {
        if (lottoNumber < MIN_LOTTO_NUMBER || lottoNumber > MAX_LOTTO_NUMBER) {
            throw new IllegalArgumentException("잘못된 범위의 로또 번호를 입력했습니다.");
        }
    }
}
