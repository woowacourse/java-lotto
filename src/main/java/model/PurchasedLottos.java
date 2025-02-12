package model;

import constant.Constants;
import java.util.ArrayList;
import java.util.List;

public class PurchasedLottos {
    private final List<LottoNumbers> lottos = new ArrayList<>();

    public PurchasedLottos(Integer purchaseAmount) {
        Integer purchaseCount = purchaseAmount / Constants.LOTTO_PRICE;
        for (int i = 0; i < purchaseCount; i++) {
            lottos.add(new LottoNumbers());
        }
    }

    public Integer size() {
        return lottos.size();
    }

    public List<LottoNumbers> getLottos() {
        return lottos;
    }
}
