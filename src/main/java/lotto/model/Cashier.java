package lotto.model;

import static lotto.rule.LottoConstants.Price.LOTTO_PRICE_UNIT;

import java.util.Objects;

public class Cashier {

    private final LottoMachine lottoMachine;

    public Cashier(LottoMachine lottoMachine) {
        Objects.requireNonNull(lottoMachine, "로또 발급기는 null이 될 수 없습니다.");
        this.lottoMachine = lottoMachine;
    }

    public LottoTicket payForLotto(int amount) {
        int count = calculateLottoCount(amount);
        return lottoMachine.issueLottoTicket(count);
    }

    private int calculateLottoCount(int amount) {
        validateAmount(amount);
        return amount / LOTTO_PRICE_UNIT;
    }

    private void validateAmount(int amount) {
        boolean isZeroAmount = amount == 0;
        if (isZeroAmount) {
            throw new IllegalArgumentException("로또를 구매하려면 최소 %,d원 이상이어야 합니다.".formatted(LOTTO_PRICE_UNIT));
        }
        boolean isNonDivisibleByUnit = amount % LOTTO_PRICE_UNIT != 0;
        if (isNonDivisibleByUnit) {
            throw new IllegalArgumentException("로또는 %,d원 단위로 구매할 수 있습니다.".formatted(LOTTO_PRICE_UNIT));
        }
    }
}
