package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import lotto.generator.LottoGenerator;

public class Lottos {

    private final List<Lotto> lottos = new ArrayList<>();

    public Lottos(List<List<Integer>> manualLottos, int autoLottoCount) {
        buyLottosByManual(manualLottos);
        buyLottosByAuto(autoLottoCount);
    }

    private void buyLottosByManual(List<List<Integer>> manualLottos) {
        lottos.addAll(manualLottos.stream()
                .map(LottoGenerator::generateLottoByManual)
                .collect(Collectors.toList()));
    }

    private void buyLottosByAuto(int autoLottoCount) {
        for (int i = 0; i < autoLottoCount; i++) {
            lottos.add(LottoGenerator.generateLottoByAuto());
        }
    }

    public List<Lotto> getLottos() {
        return Collections.unmodifiableList(lottos);
    }
}
