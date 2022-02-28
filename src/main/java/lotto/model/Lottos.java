package lotto.model;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Lottos {
    private final List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public Lottos(LottoNumberGenerator lottoNumberGenerator, int count) {
        this.lottos = generateLottos(lottoNumberGenerator, count);
    }

    private List<Lotto> generateLottos(LottoNumberGenerator lottoNumberGenerator, int count) {
        return IntStream.range(0, count)
            .mapToObj(i -> lottoNumberGenerator.generate())
            .map(Lotto::new)
            .collect(Collectors.toUnmodifiableList());
    }

    public List<Lotto> getLottos() {
        return lottos;
    }
}
