package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lotto.generator.LottoGenerator;

public class Lottos {

    private final List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        this.lottos = new ArrayList<>(lottos);
    }

    public static Lottos newInstanceByManual(List<List<Integer>> manualLottos) {
        List<Lotto> lottos = manualLottos.stream()
                .map(LottoGenerator::generateLottoByManual)
                .collect(Collectors.toList());
        return new Lottos(lottos);
    }

    public static Lottos newInstanceByAuto(int autoLottoCount) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < autoLottoCount; i++) {
            lottos.add(LottoGenerator.generateLottoByAuto());
        }
        return new Lottos(lottos);
    }

    public Lottos getCombinedLottos(Lottos combinedLottos) {
        return new Lottos(Stream.concat(lottos.stream(), combinedLottos.getLottos().stream())
                .collect(Collectors.toList()));
    }

    public List<Lotto> getLottos() {
        return Collections.unmodifiableList(lottos);
    }
}
