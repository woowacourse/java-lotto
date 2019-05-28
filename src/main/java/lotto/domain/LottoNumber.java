package lotto.domain;

public class LottoNumber {
    private static final int MIN_LOTTO_NUMBER = 1;
    private static final int MAX_LOTTO_NUMBER = 45;
    private final int number;

    public LottoNumber(int lottoNumber) {
        if (lottoNumber < MIN_LOTTO_NUMBER || lottoNumber > MAX_LOTTO_NUMBER) {
            throw new InvalidLottoNumber("로또 번호는 1 이상 45 이하여야 합니다.");
        }
        this.number = lottoNumber;
    }
}
