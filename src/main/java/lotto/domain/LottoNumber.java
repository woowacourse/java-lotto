package lotto.domain;

public class LottoNumber {

    private static final int MAX = 45;
    private static final int MIN = 1;

    private final int number;

    public LottoNumber(int number) {
        validate(number);
        this.number = number;
    }

    private void validate(int number) {
        if (!isInLottoRange(number)) {
            throw new IllegalArgumentException("로또 숫자는 1~45 사이의 숫자여야 합니다.");
        }
    }

    private boolean isInLottoRange(int number) {
        return number <= MAX && number >= MIN;
    }
}
