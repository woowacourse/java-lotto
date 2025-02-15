package service;

import error.ErrorMessage;
import factory.LottoFactory;
import java.util.ArrayList;
import java.util.List;
import model.Lotto;
import model.Lottos;
import util.RandomGenerator;

public class LottoGenerateService {
    private static final Integer PRICE = 1000;

    public Lottos generateLottos(int purchaseAmount) {
        validatePurchaseAmount(purchaseAmount);
        int count = purchaseAmount / PRICE;

        List<Lotto> lottos = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            lottos.add(LottoFactory.createRandomLotto());
        }

        return new Lottos(lottos);
    }

    private void validatePurchaseAmount(int purchaseAmount) {
        if (purchaseAmount % PRICE != 0) {
            throw new IllegalArgumentException(ErrorMessage.PURCHASE_AMOUNT_INVALID.getMessage());
        }
    }
}
