package lotto.domain.buyer;

import lotto.domain.InputNegativeException;
import lotto.domain.lotto.Lotto;

public class Budget {
    private int budget;

    public Budget(int budget) throws NoMoneyException {
        validateNotNegative(budget);
        validateAffordability(budget);
        this.budget = budget;
    }

    private void validateAffordability(int budget) throws NoMoneyException {
        if (budget < Lotto.LOTTO_PRICE) {
            throw new NoMoneyException("돈이 부족해서 로또를 구입 할 수 없습니다.");
        }
    }

    private void validateNotNegative(int budget) {
        if (budget < 0) {
            throw new InputNegativeException("잘못된 구입 금액입니다. 양수를 입력해주세요.");
        }
    }

    boolean canBuyLotto() {
        return budget >= Lotto.LOTTO_PRICE;
    }

    boolean canBuyLotto(int countOfManualLotto) {
        return budget >= Lotto.LOTTO_PRICE * countOfManualLotto;
    }

    void pay() {
        budget -= Lotto.LOTTO_PRICE;
    }

    void pay(int countOfManualLotto) {
        budget -= Lotto.LOTTO_PRICE * countOfManualLotto;
    }
}