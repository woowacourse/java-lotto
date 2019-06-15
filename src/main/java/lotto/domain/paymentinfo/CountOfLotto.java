package lotto.domain.paymentinfo;

import lotto.exception.NaturalNumberException;
import lotto.exception.PaymentOutOfBoundsException;

public class CountOfLotto {
    private static final int ZERO = 0;
    private final int countOfManualLotto;
    private final int countOfRandomLotto;

    public CountOfLotto(Payment payment, int countOfManualLotto) {
        int totalLotto = payment.calculateCountOfLotto();

        if (totalLotto < countOfManualLotto) {
            throw new PaymentOutOfBoundsException("입력한 로또 개수가 지불한 금액을 초과합니다");
        }

        if (countOfManualLotto < ZERO) {
            throw new NaturalNumberException("음수를 넣을 수 없습니다");
        }
        this.countOfManualLotto = countOfManualLotto;
        this.countOfRandomLotto = totalLotto - countOfManualLotto;
    }

    public int getTotalCountOfLotto() {
        return countOfManualLotto + countOfRandomLotto;
    }

    public int getCountOfManualLotto() {
        return countOfManualLotto;
    }

    public int getCountOfRandomLotto() {
        return countOfRandomLotto;
    }
}
