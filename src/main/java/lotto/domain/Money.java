package lotto.domain;

import java.util.Objects;

public class Money {

    private static final int ZERO = 0;
    private static final int LOTTO_PRICE = 1000;
    private static final String MONEY_EXCEPTION_MESSAGE = String
        .format("금액을 %d원 단위로 입력해주세요.", LOTTO_PRICE);

    private final int money;

    public Money(int money) {
        validatePositive(money);
        validateDivideByThousand(money);
        this.money = money;
    }

    private void validatePositive(int money) {
        if (money <= ZERO) {
            throw new IllegalArgumentException(MONEY_EXCEPTION_MESSAGE);
        }
    }

    private void validateDivideByThousand(int money) {
        if (money % LOTTO_PRICE != ZERO) {
            throw new IllegalArgumentException(MONEY_EXCEPTION_MESSAGE);
        }
    }

    public static int getBuyMoney(LottoCount lottoCount) {
        return lottoCount.getLottoCount() * LOTTO_PRICE;
    }

    public LottoCount getLottoCount() {
        return new LottoCount(money / LOTTO_PRICE);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Money money1 = (Money) o;
        return money == money1.money;
    }

    @Override
    public int hashCode() {
        return Objects.hash(money);
    }
}
