package lotto.domain;

import lotto.domain.util.CustomStringUtils;
import lotto.exception.InvalidInputException;
import lotto.exception.InvalidPaymentException;

import java.util.Objects;

public class Money {
    public static final int ONE_LOTTO_PRICE = 1000;
    private static final int MAX_BUYABLE_LOTTO_PRICE = 100000;

    private final int money;

    public Money(String input) {
        CustomStringUtils.checkIsBlank(input);
        int money = CustomStringUtils.parseInt(input);
        checkBuyableMoney(money);
        this.money = money;
    }

    private void checkBuyableMoney(int money) {
        if (money < ONE_LOTTO_PRICE) {
            throw new InvalidPaymentException("1개 이상의 로또 구입을 위해서는 1000원 이상 필요합니다.");
        }
        if (money > MAX_BUYABLE_LOTTO_PRICE) {
            throw new InvalidPaymentException("1인당 최대 10만원까지 구입 가능합니다.");
        }
    }

    public int getBuyableLottoQuantity() {
        return money / ONE_LOTTO_PRICE;
    }

    public void checkIsBuyableLottoQuantity(int quantity) {
        if (quantity > getBuyableLottoQuantity()) {
            throw new InvalidInputException("구매 가능한 갯수를 초과하였습니다.");
        }
    }

    public int getMoney() {
        return money;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money1 = (Money) o;
        return money == money1.money;
    }

    @Override
    public int hashCode() {
        return Objects.hash(money);
    }
}
