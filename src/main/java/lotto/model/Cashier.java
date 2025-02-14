package lotto.model;

import java.util.ArrayList;
import java.util.List;

public class Cashier {

    private static final int LOTTO_PURCHASE_AMOUNT_UNIT = 1_000;

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
        return amount / LOTTO_PURCHASE_AMOUNT_UNIT;
    }

    private void validateAmount(int amount) {
        if (isZeroAmount(amount)) {
            String message = String.format("로또를 구매하려면 최소 %,d원 이상이어야 합니다.", LOTTO_PURCHASE_AMOUNT_UNIT);
            throw new IllegalArgumentException(message);
        }
        if (isNonDivisibleByUnit(amount)) {
            String message = String.format("로또는 %,d원 단위로 구매할 수 있습니다.", LOTTO_PURCHASE_AMOUNT_UNIT);
            throw new IllegalArgumentException(message);
        }
    }

    private boolean isZeroAmount(int amount) {
        return amount == 0;
    }

    private boolean isNonDivisibleByUnit(int amount) {
        return amount % LOTTO_PURCHASE_AMOUNT_UNIT != 0;
    }
}
