package lotto.domain;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LottoBundle {

    private final List<Lotto> lottoBundle;

    public LottoBundle(List<Lotto> lottoBundle) {
        this.lottoBundle = lottoBundle;
    }

    public Map<Rank, Integer> makeStatistics(WinningNumbers winningNumbers) {

        Map<Rank, Integer> rankIntegerEnumMap = Arrays.stream(Rank.values())
                .collect(Collectors.toMap(rank -> rank, rank -> 0));

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

    public List<List<Integer>> getLottoBundle() {
        return lottoBundle.stream()
                .map(Lotto::getLotto)
                .toList();
    }
}
