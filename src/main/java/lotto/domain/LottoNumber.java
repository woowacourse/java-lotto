package lotto.domain;

public class LottoNumber {
    private final int lottoNumber;

    public LottoNumber(int lottoNumber) {
        validate(lottoNumber);
        this.lottoNumber = lottoNumber;
    }

    private void validate(int lottoNumber) {
        if (lottoNumber < 1 || lottoNumber > 45) {
            throw new IllegalArgumentException("범위를 벗어난 로또 숫자입니다.");
        }
    }

    public boolean isEqualTo(int number) {
        return lottoNumber == number;
    }
}
