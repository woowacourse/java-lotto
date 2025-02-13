package service;

import factory.LottoFactory;
import factory.LottosFactory;
import java.util.List;
import model.Lotto;
import model.Lottos;
import util.RandomGenerator;

public class LottoGenerateService {
    private static final Integer PRICE = 1000;

    public Lottos generateLottos(int purchaseAmount) {
        validatePurchaseAmount(purchaseAmount);
        int count = purchaseAmount / PRICE;

        Lottos lottos = LottosFactory.createLottos();

        for (int i = 0; i < count; i++) {
            insertLotto(lottos);
        }

        return lottos;
    }

    private void insertLotto(Lottos lottos) {
        List<Integer> numbers = RandomGenerator.generateNumbers(1, 45, 6);
        Lotto lotto = LottoFactory.createLotto(numbers);
        lottos.addLotto(lotto);
    }

    private void validatePurchaseAmount(int purchaseAmount) {
        if (purchaseAmount % PRICE != 0) {
            throw new IllegalArgumentException("구입 금액은 1000원 단위로만 가능합니다.");
        }
    }
}
