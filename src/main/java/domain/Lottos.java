package domain;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Lottos {
    private final List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        validateNullOrEmpty(lottos);
        this.lottos = Collections.unmodifiableList(lottos);
    }

    private void validateNullOrEmpty(List<Lotto> lottos) {
        if (Objects.isNull(lottos) || lottos.isEmpty()) {
            throw new IllegalArgumentException("Lotto 목록이 비었습니다");
        }
    }
}
