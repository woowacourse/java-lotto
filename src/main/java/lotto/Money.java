package lotto;

import lotto.exception.InvalidPaymentException;
import org.apache.commons.lang3.StringUtils;

public class Money {
    private final int money;

    public Money(String input) {
        checkIsBlank(input);
        int money = parseInt(input);
        checkBuyableMoney(money);
        this.money = money;
    }

    private void checkIsBlank(String input) {
        if (StringUtils.isBlank(input)) {
            throw new InvalidPaymentException("아무것도 입력하지 않으셨습니다.");
        }
    }

    private int parseInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new InvalidPaymentException("금액을 숫자로 입력해주세요.");
        }
    }

    private void checkBuyableMoney(int money) {
        if (money < 1000) {
            throw new InvalidPaymentException("1개 이상의 로또 구입을 위해서는 1000원 이상 필요합니다.");
        }
        if (money > 100000) {
            throw new InvalidPaymentException("1인당 최대 10만원까지 구입 가능합니다.");
        }
    }
}
