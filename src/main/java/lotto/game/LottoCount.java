package lotto.game;

import lotto.money.Money;
import lotto.ticket.TicketValidation;

import java.util.Objects;

public class LottoCount {
    public static final int ZERO = 0;
    public static final int ONE_COUNT = 1;
    private static final int LOTTO_PRICE = 1000;

    private final int lottoCount;

    public LottoCount(Money money) {
        this(money.divideMoney(LOTTO_PRICE));
    }

    public LottoCount(String count) {
        TicketValidation.validateNumber(count);
        this.lottoCount = Integer.parseInt(count);
    }

    public boolean isGreaterThanZero() {
        return this.lottoCount > ZERO;
    }

    public LottoCount decreaseOne() {
        return new LottoCount(String.valueOf(lottoCount - ONE_COUNT));
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