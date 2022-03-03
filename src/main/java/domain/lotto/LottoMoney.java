package domain.lotto;

import exception.lottoMoney.LottoMoneyDivideException;
import exception.lottoMoney.LottoMoneyLessException;
import java.util.Objects;

public class LottoMoney {
    private static final int LOTTO_MONEY_DIVIDE_UNIT = 1000;
    private final int lottoMoney;

    private LottoMoney(final int lottoMoney) {
        validate(lottoMoney);
        this.lottoMoney = lottoMoney;
    }

    public static LottoMoney from(final int money) {
        return new LottoMoney(money);
    }

    private static void validate(final int money) {
        if (money < LOTTO_MONEY_DIVIDE_UNIT) {
            throw new LottoMoneyLessException();
        }
        if (money % LOTTO_MONEY_DIVIDE_UNIT != 0) {
            throw new LottoMoneyDivideException();
        }
    }

    public int toLottoCount() {
        return lottoMoney / 1000;
    }

    public int get() {
        return lottoMoney;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LottoMoney lottoMoney1 = (LottoMoney) o;
        return lottoMoney == lottoMoney1.lottoMoney;
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottoMoney);
    }
}