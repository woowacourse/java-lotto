package lotto;

import lotto.domain.lotto.LottoStore;
import lotto.domain.lotto.NumManualLotto;

public class Money {

    private static final String ERROR_POSITIVE = "음수는 불가능합니다.";
    private static final String ERROR_CONVERT_INT = "공백이나 숫자가 아닌 값은 불가능합니다.";
    private static final String ERROR_BUY_FAIL = "구매 할 수 없는 로또 수량입니다.";
    private int price;

    public Money(String money) {
        int price = convertInt(money);
        validPositive(price);
        this.price = price;
    }

    private int convertInt(String price) {
        try {
            return Integer.parseInt(price);
        } catch (Exception error) {
            throw new IllegalArgumentException(ERROR_CONVERT_INT);
        }
    }

    private void validPositive(int price) {
        if (price < 0) {
            throw new IllegalArgumentException(ERROR_POSITIVE);
        }
    }

    public int getPrice() {
        return this.price;
    }

    public void validNumManual(NumManualLotto numManual) {
        if (price - (numManual.getNumLotto() * LottoStore.LOTTO_PRICE) < 0) {
            throw new IllegalArgumentException(ERROR_BUY_FAIL);
        }
    }
}