package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lottos {

    private final List<Lotto> lottos;

    public Lottos(final List<Lotto> lottos, final int totalCount) {
        List<Lotto> newLottos = new ArrayList<>(lottos);
        int count = totalCount - lottos.size();
        for (int i = 0; i < count; i++) {
            newLottos.add(Lotto.createAutoLotto());
        }
        this.lottos = newLottos;
    }

    public List<Rank> matchRanks(LottoWinningNumbers winningLotto) {
        List<Rank> ranks = new ArrayList<>();
        for (Lotto lotto : lottos) {
            int matchCount = winningLotto.matchCount(lotto);
            boolean hasBonusNumber = lotto.contains(winningLotto.getBonusNumber());
            Rank rank = Rank.matchRank(matchCount, hasBonusNumber);
            ranks.add(rank);
        }
        return ranks;
    }

    public int getSize() {
        return lottos.size();
    }

    public List<Lotto> getLottos() {
        return Collections.unmodifiableList(lottos);
    }
}
