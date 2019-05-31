package lotto.domain.lotto;

import lotto.domain.money.Prize;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lottos {
    private List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        this.lottos = new ArrayList<>(lottos);
    }

    public List<Prize> getPrizes(WinningLotto winningLotto) {
        List<Prize> prizes = new ArrayList<>();
        for (Lotto lotto : lottos) {
            prizes.add(winningLotto.prizeOf(lotto));
        }
        return prizes;
    }

    public Lottos append(Lottos otherLottos) {
        List<Lotto> appendedLottos = new ArrayList<>(lottos);
        appendedLottos.addAll(otherLottos.lottos);

        return new Lottos(appendedLottos);
    }

    public int size() {
        return lottos.size();
    }

    public List<Lotto> getLottos() {
        return Collections.unmodifiableList(lottos);
    }
}
