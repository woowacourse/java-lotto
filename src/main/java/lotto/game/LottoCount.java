package lotto.game;

import lotto.money.Money;
import lotto.ticket.Ticket;

import java.util.Objects;

import static lotto.ticket.Number.ERROR_MESSAGE_INVALID_INPUT;
import static lotto.ticket.Number.validateNumber;

public class LottoCount {
    public static final String ERROR_MESSAGE_INVALID_AMOUNT = "구매 금액보다 많이 구입할 수 없습니다.";
    public static final int ZERO = 0;
    public static final int ONE_COUNT = 1;

    private final int lottoCount;

    public LottoCount(Money money) {
        this(money.divideMoney(Ticket.PRICE));
    }

    public LottoCount(String value) {
        this.lottoCount = validate(value);
    }

    private int validate(String value) {
        int number = validateNumber(value);
        validateNotNegative(number);
        return number;
    }

    private void validateNotNegative(int number) {
        if (number < ZERO) {
            throw new IllegalArgumentException(ERROR_MESSAGE_INVALID_INPUT);
        }
    }

    public boolean isGreaterThanZero() {
        return this.lottoCount > ZERO;
    }

    public LottoCount decreaseOne() {
        return new LottoCount(String.valueOf(lottoCount - ONE_COUNT));
    }

    public void checkPurchasePossibility(LottoCount count) {
        validateAmount(lottoCount, count);
    }

    public LottoCount consumeTicket(LottoCount count) {
        validateAmount(lottoCount, count);
        return count.remainCount(lottoCount);
    }

    private void validateAmount(int currentCount, LottoCount count) {
        if (!count.canPurchase(currentCount)) {
            throw new IllegalArgumentException(ERROR_MESSAGE_INVALID_AMOUNT);
        }
    }

    private LottoCount remainCount(int lottoCount) {
        return new LottoCount(String.valueOf(lottoCount - this.lottoCount));
    }

    public boolean canPurchase(int currentCount) {
        return currentCount >= this.lottoCount;
    }

    public int getLottoCount() {
        return lottoCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoCount that = (LottoCount) o;
        return lottoCount == that.lottoCount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottoCount);
    }
}