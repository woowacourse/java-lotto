package service;

import domain.Lotto;
import domain.Lottos;
import error.ErrorMessage;
import java.util.List;
import java.util.stream.IntStream;

public class LottoGenerateService {

    private static final Integer PRICE = 1000;

    public Lottos generateLottos(int purchaseAmount, NumbersStrategy strategy) {
        validatePurchaseAmount(purchaseAmount);
        int count = purchaseAmount / PRICE;

        List<Lotto> lottos = IntStream.range(0, count)
            .mapToObj(repeat -> new Lotto(strategy.get()))
            .toList();
        return new Lottos(lottos);
    }

    private void validatePurchaseAmount(int purchaseAmount) {
        if (purchaseAmount % PRICE != 0) {
            throw new IllegalArgumentException(ErrorMessage.PURCHASE_AMOUNT_INVALID.getMessage());
        }
    }
}
