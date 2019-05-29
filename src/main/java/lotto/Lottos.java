package lotto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
}
