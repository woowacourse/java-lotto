package lotto.domain;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class PurchaseLottos {
    private static final String EMPTY_OR_NULL_INPUT_EXCEPTION_MESSAGE = "Null or empty input to PurchaseLottos.";

    private final List<Lotto> purchaseLottos;

    public PurchaseLottos(List<Lotto> purchaseLottos) {
        validateEmptyOrNull(purchaseLottos);
        this.purchaseLottos = purchaseLottos;
    }

    private void validateEmptyOrNull(List<Lotto> purchaseLottos) {
        if (Objects.isNull(purchaseLottos) || purchaseLottos.isEmpty()) {
            throw new IllegalArgumentException(EMPTY_OR_NULL_INPUT_EXCEPTION_MESSAGE);
        }
    }

    public List<Lotto> getPurchaseLottos() {
        return Collections.unmodifiableList(purchaseLottos);
    }
}
