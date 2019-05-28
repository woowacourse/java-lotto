package lotto;

public class LottoNumber {
    private final int number;

    public LottoNumber(int number) {
        validLottoNumber(number);
        this.number = number;
    }

    private void validLottoNumber(int number) {
        if (1 > number || number > 45) {
            throw new IllegalArgumentException();
        }
    }
}
