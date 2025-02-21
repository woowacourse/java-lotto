package service;

import error.ErrorMessage;
import factory.LottoFactory;
import java.util.ArrayList;
import java.util.List;
import model.Lotto;
import model.Lottos;

public class LottoGenerateService {
    private final LottoFactory lottoFactory;

    public LottoGenerateService(LottoFactory lottoFactory) {
        this.lottoFactory = lottoFactory;
    }

    public Lottos generateLottos(int purchaseAmount) {
        validatePurchaseAmount(purchaseAmount);
        int count = purchaseAmount / Lotto.PRICE;

        List<Lotto> lottos = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            lottos.add(lottoFactory.createLotto());
        }

        return new Lottos(lottos);
    }

    private void validatePurchaseAmount(int purchaseAmount) {
        if (purchaseAmount % Lotto.PRICE != 0) {
            throw new IllegalArgumentException(ErrorMessage.PURCHASE_AMOUNT_INVALID.getMessage());
        }
    }
}
