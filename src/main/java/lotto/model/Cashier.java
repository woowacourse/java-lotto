package lotto.model;

import java.util.ArrayList;
import java.util.List;

public final class Cashier {

    private static final int LOTTO_PURCHASE_AMOUNT_UNIT = 1_000;

    private Cashier() {
    }

    public static List<Lotto> payForLotto(int amount) {
        int count = calculateLottoCount(amount);
        return issueLottoByCount(count);
    }

    private static List<Lotto> issueLottoByCount(int count) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            lottos.add(LottoMachine.issue());
        }
        return List.copyOf(lottos);
    }

    private static int calculateLottoCount(int amount) {
        if (amount % LOTTO_PURCHASE_AMOUNT_UNIT != 0) {
            String message = String.format("로또는 %,d원 단위로 구매할 수 있습니다.", LOTTO_PURCHASE_AMOUNT_UNIT);
            throw new IllegalArgumentException(message);
        }
        return amount / LOTTO_PURCHASE_AMOUNT_UNIT;
    }
}
