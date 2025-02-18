package lotto.domain;

import lotto.constant.WinningTier;

import java.util.ArrayList;
import java.util.List;

public class Lottos {
    private final List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public int getLottoCount() {
        return this.lottos.size();
    }

    public List<Lotto> getLottos() {
        return this.lottos;
    }

    public List<WinningTier> getWinningTiers(WinningLotto winningLotto) {
        List<WinningTier> winningTiers = new ArrayList<>();
        for (Lotto lotto : this.lottos) {
            WinningTier tier = winningLotto.findWinningTier(lotto);
            winningTiers.add(tier);
        }
        return winningTiers;
    }
}
