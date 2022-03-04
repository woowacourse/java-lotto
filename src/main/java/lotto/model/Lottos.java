package lotto.model;

import static java.util.stream.Collectors.*;
import static lotto.ValidationUtils.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import lotto.model.numbergenerator.LottoNumberGenerator;

public class Lottos {
    private final List<Lotto> lottos;

    Lottos(LottoNumberGenerator lottoNumberGenerator, int count) {
        this.lottos = generateLottos(lottoNumberGenerator, count);
        validateNullCollection(this.lottos);
    }

    private List<Lotto> generateLottos(LottoNumberGenerator lottoNumberGenerator, int count) {
        return List.copyOf(IntStream.range(0, count)
            .mapToObj(i -> lottoNumberGenerator.generate())
            .map(Lotto::new)
            .collect(toList()));
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
