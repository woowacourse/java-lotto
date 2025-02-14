package lotto.domain;

import java.util.EnumMap;
import java.util.List;

public class LottoBundle {

    private final List<Lotto> lottoBundle;

    public LottoBundle(List<Lotto> lottoBundle) {
        this.lottoBundle = lottoBundle;
    }

    public EnumMap<Rank, Integer> makeStatistics(WinningNumbers winningNumbers) {

        EnumMap<Rank, Integer> rankIntegerEnumMap = Rank.makeDefaultMap();

        for (Lotto lotto : lottoBundle) {
            Rank currentRank = Rank.checkPrize(winningNumbers.checkMatchCount(lotto),
                    winningNumbers.checkMatchBonus(lotto));
            rankIntegerEnumMap.put(currentRank, rankIntegerEnumMap.get(currentRank) + 1);
        }

        return rankIntegerEnumMap;
    }

    public int getLottoQuantity() {
        return lottoBundle.size();
    }

    public List<Lotto> getLottoBundle() {
        return lottoBundle;
    }
}
