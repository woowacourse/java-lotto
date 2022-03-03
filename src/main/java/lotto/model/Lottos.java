package lotto.model;

import static lotto.ValidationUtils.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import lotto.model.numbergenerator.LottoNumberGenerator;

public class Lottos {
    private final List<Lotto> lottos;

    public Lottos(LottoNumberGenerator lottoNumberGenerator, int count) {
        this.lottos = new ArrayList<>(generateLottos(lottoNumberGenerator, count));
        validateEmptyCollection(this.lottos);
    }

    private List<Lotto> generateLottos(LottoNumberGenerator lottoNumberGenerator, int count) {
        return IntStream.range(0, count)
            .mapToObj(i -> lottoNumberGenerator.generate())
            .map(Lotto::new)
            .collect(Collectors.toUnmodifiableList());
    }

    public List<Lotto> getLottos() {
        return Collections.unmodifiableList(lottos);
    }
}
