package domain;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Lottos {
    private static final String ERROR_MESSAGE_FOR_NULL_OR_EMPTY_LIST = "Lotto 목록이 비었습니다";
    private final List<Lotto> lottos;

    public Lottos(final List<Lotto> lottos) {
        Objects.requireNonNull(lottos, ERROR_MESSAGE_FOR_NULL_OR_EMPTY_LIST);
        final List<Lotto> unmodifiableLottos = Collections.unmodifiableList(lottos);
        validateEmpty(lottos);

        this.lottos = unmodifiableLottos;
    }

    private void validateEmpty(List<Lotto> lottos) {
        if (lottos.isEmpty()) {
            throw new IllegalArgumentException(ERROR_MESSAGE_FOR_NULL_OR_EMPTY_LIST);
        }
    }

    public List<Lotto> getLottos() {
        return lottos;
    }
}
