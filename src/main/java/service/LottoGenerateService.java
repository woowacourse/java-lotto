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

        Lottos lottos = new Lottos();
        IntStream.range(0, count)
            .forEach(repeat -> insertNewLottoTo(lottos, strategy));
        return lottos;
    }

    private void insertNewLottoTo(Lottos lottos, NumbersStrategy strategy) {
        List<Integer> numbers = strategy.get();
        Lotto lotto = new Lotto(numbers);
        lottos.addLotto(lotto);
    }

    private void validatePurchaseAmount(int purchaseAmount) {
        if (purchaseAmount % PRICE != 0) {
            throw new IllegalArgumentException(ErrorMessage.PURCHASE_AMOUNT_INVALID.getMessage());
        }
    }
}
