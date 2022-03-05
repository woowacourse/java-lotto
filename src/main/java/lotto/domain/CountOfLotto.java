package lotto.domain;

import lotto.validator.CountOfLottoValidator;

public class CountOfLotto {

    private final int countOfManualLotto;
    private final int countOfAutoLotto;

    public CountOfLotto(int countOfManualLotto, Money money) {
        CountOfLottoValidator.validate(countOfManualLotto, money.getMoney());
        this.countOfManualLotto = countOfManualLotto;
        this.countOfAutoLotto = getCountOfAutoLotto(countOfManualLotto, money);
    }

    private int getCountOfAutoLotto(int countOfManualLotto, Money money) {
        return (int) (money.getMoney() / Lotto.LOTTO_PRICE) - countOfManualLotto;
    }

    public int getCountOfManualLotto() {
        return countOfManualLotto;
    }

    public int getCountOfAutoLotto() {
        return countOfAutoLotto;
    }
}
