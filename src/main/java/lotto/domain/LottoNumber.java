package lotto.domain;

public class LottoNumber {
    private static final int MIN_LOTTO_NUMBER = 1;
    private static final int MAX_LOTTO_NUMBER = 45;
    private final int number;

    public LottoNumber(int number) {
        this.number = number;
        checkValidRange();
    }

    private void checkValidRange() {
        if (number < MIN_LOTTO_NUMBER || number > MAX_LOTTO_NUMBER) {
            throw new IllegalArgumentException("유효한 로또 번호가 아닙니다.");
        }
    }
}
