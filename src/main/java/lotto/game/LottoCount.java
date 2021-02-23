package lotto.game;

import lotto.money.Money;
import lotto.ticket.Ticket;
import lotto.ticket.TicketValidation;

import java.util.Objects;

public class LottoCount {
    public static final int ZERO = 0;
    public static final int ONE_COUNT = 1;

    private final int lottoCount;

    public LottoCount(Money money) {
        this(money.divideMoney(Ticket.PRICE));
    }

    public LottoCount(String value) {
        TicketValidation.validateNumber(value);
        this.lottoCount = Integer.parseInt(value);
    }

    public boolean isGreaterThanZero() {
        return this.lottoCount > ZERO;
    }

    public LottoCount decreaseOne() {
        return new LottoCount(String.valueOf(lottoCount - ONE_COUNT));
    }

    public void checkPurchasePossibility(LottoCount count) {
        TicketValidation.validateAmount(lottoCount, count);
    }

    public LottoCount consumeTicket(LottoCount count) {
        TicketValidation.validateAmount(lottoCount, count);
        return count.remainCount(lottoCount);
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