package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lottos {

    private final List<Lotto> lottos;

    private Lottos(List<Lotto> lottos) {
        this.lottos = new ArrayList<>(lottos);
    }

    public static Lottos buyLottosByAuto(Money money) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < LottoCounter.getTotalLottoCount(money); i++) {
            lottos.add(Lotto.generateLottoByAuto());
        }
        return new Lottos(lottos);
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

    public int getTotalLottoCount() {
        return lottos.size();
    }
}
