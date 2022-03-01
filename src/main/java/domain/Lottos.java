package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lottos {

    private final List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        this.lottos = new ArrayList<>(lottos);
    }

    public List<LottoReward> calculateLottoReward(WinningLotto winningLotto) {
        List<LottoReward> lottoRewards = new ArrayList<>();
        for (Lotto lotto : lottos) {
            lottoRewards.add(winningLotto.calculateMatchResult(lotto));
        }

        return lottoRewards;
    }

    public List<Lotto> getLottos() {
        return Collections.unmodifiableList(lottos);
    }
}
