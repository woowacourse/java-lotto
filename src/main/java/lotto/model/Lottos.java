package lotto.model;

import static java.util.stream.Collectors.*;
import static lotto.ValidationUtils.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import lotto.model.lottofactory.Lotto;
import lotto.model.lottofactory.LottoFactory;

public class Lottos {
    private final List<Lotto> lottos;

    Lottos(LottoFactory factory, int count) {
        this.lottos = generateLottos(factory, count);
        validateNullCollection(this.lottos);
    }

    private List<Lotto> generateLottos(LottoFactory factory, int count) {
        return IntStream.range(0, count)
            .mapToObj(i -> factory.generate())
            .collect(toUnmodifiableList());
    }

    public List<Lotto> getLottos() {
        return List.copyOf(lottos);
    }

    List<Lotto> getTotalLottos(Lottos otherLottos) {
        List<Lotto> thisLottos = new ArrayList<>(this.lottos);
        thisLottos.addAll(otherLottos.getLottos());
        return List.copyOf(thisLottos);
    }
}
