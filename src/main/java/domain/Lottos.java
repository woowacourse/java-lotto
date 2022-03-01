package domain;

import static java.util.stream.Collectors.toList;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class Lottos {
    private static final String ERROR_MESSAGE_FOR_NULL_OR_EMPTY_LIST = "Lotto 목록이 비었습니다";

    private final List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        Objects.requireNonNull(lottos, ERROR_MESSAGE_FOR_NULL_OR_EMPTY_LIST);
        List<Lotto> copiedLottos = List.copyOf(lottos);
        validateEmpty(lottos);

        this.lottos = copiedLottos;
    }

    private void validateEmpty(List<Lotto> lottos) {
        if (lottos.isEmpty()) {
            throw new IllegalArgumentException(ERROR_MESSAGE_FOR_NULL_OR_EMPTY_LIST);
        }
    }

    public Lottos merge(Lottos anotherLottos) {
        List<Lotto> mergedLottos = Stream.of(this.lottos, anotherLottos.lottos)
                .flatMap(Collection::stream)
                .collect(toList());

        return new Lottos(mergedLottos);
    }

    public List<Lotto> getLottos() {
        return lottos;
    }
}
