package domain;

import domain.strategy.LottoNumberGenerateStrategy;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Lottos {
    private final List<Lotto> lottos;

    private Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public static Lottos of(LottoQuantity lottoQuantity, LottoNumberGenerateStrategy lottoNumberGenerator) {
        return new Lottos(generateLottos(lottoQuantity, lottoNumberGenerator));
    }

    public static Lottos from(List<Lotto> lottos) {
        return new Lottos(lottos);
    }

    public static Lottos fromRawValues(List<Set<Integer>> values) {
        return new Lottos(values.stream()
                .map(Lotto::fromRawValues)
                .collect(Collectors.toList()));
    }

    private static List<Lotto> generateLottos(LottoQuantity lottoQuantity,
                                              LottoNumberGenerateStrategy lottoNumberGenerator) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < lottoQuantity.getLottoQuantity(); i++) {
            lottos.add(Lotto.from(lottoNumberGenerator.generateNumbers()));
        }

        return lottos;
    }

    public static Lottos concat(Lottos lottos1, Lottos lottos2) {
        List<Lotto> newLottoValues = new ArrayList<>(lottos1.lottos);
        newLottoValues.addAll(lottos2.lottos);

        return new Lottos(newLottoValues);
    }

    public List<Lotto> getLottos() {
        return lottos;
    }

    public int getSize() {
        return lottos.size();
    }

    @Override
    public String toString() {
        return "Lottos{" +
                "lottos=" + lottos +
                '}';
    }
}
