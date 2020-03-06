package lotto.domain;

import lotto.domain.exception.EmptyOrNullException;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class PurchaseLottos {
    private static final String EMPTY_OR_NULL_INPUT_EXCEPTION_MESSAGE = "Null or empty input to PurchaseLottos.";

    private final List<Lotto> purchaseLottos;

    private PurchaseLottos(List<Lotto> purchaseLottos) {
        validateEmptyOrNull(purchaseLottos);
        this.purchaseLottos = purchaseLottos;
    }

    public static PurchaseLottos makePurchaseLottos(List<Lotto> purchaseLottos, int randomLottoAmount) {
        for (int i = 0; i < randomLottoAmount; i++) {
            List<Integer> randomNumbers = RandomNumberGenerator.generateNumbers();
            purchaseLottos.add(new Lotto(randomNumbers));
        }
        return new PurchaseLottos(purchaseLottos);
    }

    private void validateEmptyOrNull(List<Lotto> purchaseLottos) {
        if (Objects.isNull(purchaseLottos) || purchaseLottos.isEmpty()) {
            throw new EmptyOrNullException(EMPTY_OR_NULL_INPUT_EXCEPTION_MESSAGE);
        }
    }

    public List<Lotto> getPurchaseLottos() {
        return Collections.unmodifiableList(purchaseLottos);
    }
}
