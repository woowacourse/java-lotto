package lotto.model;

import static lotto.LottoConstants.Price.LOTTO_PRICE_UNIT;

import java.util.ArrayList;
import java.util.List;

public class Cashier {

    public List<Lotto> payForLotto(int amount) {
        int count = calculateLottoCount(amount);
        return issueLottoByCount(count);
    }

    private List<Lotto> issueLottoByCount(int count) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            lottos.add(LottoMachine.issue());
        }
        return List.copyOf(lottos);
    }

    private int calculateLottoCount(int amount) {
        validateAmount(amount);
        return amount / LOTTO_PRICE_UNIT;
    }

    private void validateAmount(int amount) {
        if (isZeroAmount(amount)) {
            throw new IllegalArgumentException("로또를 구매하려면 최소 %,d원 이상이어야 합니다.".formatted(LOTTO_PRICE_UNIT));
        }
        if (isNonDivisibleByUnit(amount)) {
            throw new IllegalArgumentException("로또는 %,d원 단위로 구매할 수 있습니다.".formatted(LOTTO_PRICE_UNIT));
        }
    }

    private boolean isZeroAmount(int amount) {
        return amount == 0;
    }

    private boolean isNonDivisibleByUnit(int amount) {
        return amount % LOTTO_PRICE_UNIT != 0;
    }
}
