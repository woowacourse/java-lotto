package lotto.domain;

public class BonusNumber {
    private LottoNumber number;

    public BonusNumber(int number) {
        this.number = LottoNumber.getNumber(number);
    }

    public LottoNumber getNumber() {
        return number;
    }
}
