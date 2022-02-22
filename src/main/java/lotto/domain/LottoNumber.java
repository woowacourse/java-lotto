package lotto.domain;

public class LottoNumber {

    private final int number;

    public LottoNumber(final int number) {
        if (number < 1 || number > 45) {
            throw new IllegalArgumentException("");
        }
        this.number = number;
    }
}
