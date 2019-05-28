package lotto.domain;

public class LottoNumber {
    private final int number;

    public LottoNumber(int number) {
        checkValidRange(number);
        this.number = number;
    }

    private void checkValidRange(int number) {
        if (number < 1 || number > 45) {
            throw new IllegalArgumentException("유효한 로또 번호가 아닙니다.");
        }
    }
}
