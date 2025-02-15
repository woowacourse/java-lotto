package lotto.domain;

import lotto.constant.WinningTier;

import java.util.ArrayList;
import java.util.List;

public class Scoreboard {

    public List<WinningTier> findWinningTiers(List<Lotto> lottos, WinningLotto winningLotto) {
        List<WinningTier> winningTiers = new ArrayList<>();
        for (Lotto lotto : lottos) {
            WinningTier tier = winningLotto.findWinningTier(lotto);
            winningTiers.add(tier);
        }
        return winningTiers;
    }
}
