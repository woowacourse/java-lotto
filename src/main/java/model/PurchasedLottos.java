package model;

import java.util.ArrayList;
import java.util.List;

public class PurchasedLottos {
    private static final int LOTTO_PRICE = 1000;
    private static final String DIVIDABLE_EXCEPTION = "1000의 배수를 입력해주세요.";

    private final List<Lotto> lottos = new ArrayList<>();
    private final Integer purchaseAmount;

    public PurchasedLottos(Integer purchaseAmount) {
        validateDividable(purchaseAmount);
        int purchaseCount = purchaseAmount / LOTTO_PRICE;
        for (int i = 0; i < purchaseCount; i++) {
            lottos.add(new Lotto());
        }
        this.purchaseAmount = purchaseAmount;
    }

    public Integer size() {
        return lottos.size();
    }

    public List<Lotto> getLottos() {
        return lottos;
    }

    public Integer getPurchaseAmount() {
        return purchaseAmount;
    }

    private void validateDividable(Integer input) {
        if (input % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException(DIVIDABLE_EXCEPTION);
        }
    }
}
