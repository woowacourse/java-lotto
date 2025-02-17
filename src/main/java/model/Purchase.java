package model;

import static model.LottoNumbers.LOTTO_PRICE;

import java.util.ArrayList;
import java.util.List;

public class Purchase {
    private final List<LottoNumbers> lottos = new ArrayList<>();
    private final int purchaseAmount;

    public Purchase(int purchaseAmount) {
        int purchaseCount = purchaseAmount / LOTTO_PRICE;
        for (int i = 0; i < purchaseCount; i++) {
            lottos.add(new LottoNumbers());
        }
        this.purchaseAmount = purchaseAmount;
    }


    public int size() {
        return lottos.size();
    }

    public List<LottoNumbers> getLottos() {
        return lottos;
    }

    public int getPurchaseAmount() {
        return purchaseAmount;
    }
}
