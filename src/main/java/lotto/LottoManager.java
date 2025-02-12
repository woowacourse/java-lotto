package lotto;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class LottoManager {
    private static final int LOTTO_UNIT_PRICE = 1000;

    public List<List<Integer>> purchase(final int purchaseAmount) {
        validatePurchaseAmount(purchaseAmount);
        int lottoAmount = purchaseAmount / LOTTO_UNIT_PRICE;
        List<List<Integer>> lottos = new ArrayList<>();
        for (int i = 0; i < lottoAmount; i++) {
            List<Integer> lotto = new LottoMachine().createLotto();
            lottos.add(lotto);
        }
        return lottos;
    }

    private void validatePurchaseAmount(final int purchaseAmount) {
        if (purchaseAmount % LOTTO_UNIT_PRICE != 0) {
            throw new IllegalArgumentException("구입금액은 1000원으로 나누어져야 합니다.");
        }

        if (purchaseAmount <= 0) {
            throw new IllegalArgumentException("구입금액은 양수여야 합니다.");
        }
    }
}
