package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lottos {

    private final List<Lotto> lottos;

    public Lottos() {
        this.lottos = new ArrayList<>();
    }

    public void buyLottosByManual(List<Lotto> manualLottos) {
        lottos.addAll(manualLottos);
    }

    public void buyLottosByAuto(int autoLottoCount) {
        for (int i = 0; i < autoLottoCount; i++) {
            lottos.add(Lotto.generateLottoByAuto());
        }
    }

    public List<Lotto> getLottos() {
        return Collections.unmodifiableList(lottos);
    }
}
