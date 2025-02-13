package model;

import constant.Constants;
import java.util.ArrayList;
import java.util.List;

public class Purchase {
    private final List<LottoNumbers> lottos = new ArrayList<>();
    private final Integer purchaseAmount;

    public Purchase(Integer purchaseAmount) {
        Integer purchaseCount = purchaseAmount / Constants.LOTTO_PRICE;
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
}
