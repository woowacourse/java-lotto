package service;

import java.awt.print.Pageable;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
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

        for(int i = 0; i < count; i++) {
            List<Integer> numbers = RandomGenerator.generateNumbers(1, 45, 6);
            Collections.sort(numbers);
            Lotto lotto = new Lotto(numbers);
            lottos.addLotto(lotto);
        }

        return lottos;
    }

    private void validatePurchaseAmount(int purchaseAmount) {
        if (purchaseAmount % PRICE != 0) {
            throw new IllegalArgumentException("구입 금액은 1000원 단위로만 가능합니다.");
        }
    }
}
