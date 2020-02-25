package lotto.domain;

import static lotto.domain.NumberValidator.validateIfEmptyValueThenInvokeException;
import static lotto.domain.NumberValidator.validateIfNotNumberThenInvokeException;

public class LottoMoney {
    private static final int MONEY_PER_LOTTO = 1_000;
    private static final int PERCENT = 100;

    private final int value;

    public LottoMoney(String money) {
        validate(money);
        this.value = Integer.parseInt(money);
    }

    private static void validateIfSmallerThanMinMoneyThenInvokeException(String money, String message) {
        int moneyValue = Integer.parseInt(money);
        if (moneyValue < MONEY_PER_LOTTO) {
            throw new RuntimeException(message);
        }
    }

    private void validate(String money) {
        validateIfEmptyValueThenInvokeException(money, "구입금액을 입력해 주세요.");
        validateIfNotNumberThenInvokeException(money, "구입금액은 숫자만 입력 가능합니다.");
        validateIfSmallerThanMinMoneyThenInvokeException(money, String.format("최소 %d원 이상 구매하셔야 합니다.", MONEY_PER_LOTTO));
    }

    public int calculateLottoCount() {
        return value / MONEY_PER_LOTTO;
    }

    public int calculateEarningsRate(int totalEarnings) {
        double ratio = totalEarnings / (double) value;
        return (int) Math.round(ratio * PERCENT);
    }
}
