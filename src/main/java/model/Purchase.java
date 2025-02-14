package model;

import java.util.ArrayList;
import java.util.List;

public class Purchase {
    private static final int LOTTO_PRICE = 1000;
    private final List<LottoNumbers> lottos = new ArrayList<>();
    private final Integer purchaseAmount;

    public Purchase(Integer purchaseAmount) {
        Integer purchaseCount = purchaseAmount / LOTTO_PRICE;
        for (int i = 0; i < purchaseCount; i++) {
            lottos.add(new LottoNumbers());
        }
        this.purchaseAmount = purchaseAmount;
    }


    public Integer size() {
        return lottos.size();
    }

    public List<LottoNumbers> getLottos() {
        return lottos;
    }

    public Integer getPurchaseAmount() {
        return purchaseAmount;
    }
}
