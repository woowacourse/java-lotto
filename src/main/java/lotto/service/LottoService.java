package lotto.service;

import lotto.domain.Lotto;

public class LottoService {

    public int purchaseLotto(int purchaseAmount) {
        return purchaseAmount / Lotto.LOTTO_PRICE;
    }

}
