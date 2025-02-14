package service;

import error.ErrorMessage;
import java.util.List;
import model.Lotto;
import model.Lottos;
import util.RandomGenerator;

public class LottoGenerateService {
    private static final Integer PRICE = 1000;

    public Lottos generateLottos(int purchaseAmount) {
        validatePurchaseAmount(purchaseAmount);
        int count = purchaseAmount / PRICE;

        Lottos lottos = new Lottos();

        for (int i = 0; i < count; i++) {
            insertLotto(lottos);
        }

        return lottos;
    }

    private void insertLotto(Lottos lottos) {
        List<Integer> numbers = RandomGenerator.generateNumbers(1, 45, 6);
        Lotto lotto = new Lotto(numbers);
        lottos.addLotto(lotto);
    }

    private void validatePurchaseAmount(int purchaseAmount) {
        if (purchaseAmount % PRICE != 0) {
            throw new IllegalArgumentException(ErrorMessage.PURCHASE_AMOUNT_INVALID.getMessage());
        }
    }
}
