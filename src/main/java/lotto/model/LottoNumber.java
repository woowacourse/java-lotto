package lotto.model;

public class LottoNumber {

    protected int number;

    public LottoNumber(int number) {
        validateRange(number);
        this.number = number;
    }

    private void validateRange(int number) {
        if (number < 1 || number > 45) {
            throw new RuntimeException();
        }
    }

    public int getLottoNumber() {
        return number;
    }
}
