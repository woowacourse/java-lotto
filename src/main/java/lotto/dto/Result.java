package lotto.dto;

import java.util.EnumMap;
import java.util.List;
import lotto.domain.LottoWinningNumbers;
import lotto.domain.Lottos;
import lotto.domain.Rank;

public class Result {

    private final EnumMap<Rank, Integer> result;

    public Result(LottoWinningNumbers lottoWinningNumbers, Lottos lottos) {
        this.result = calculateMatchRanks(lottoWinningNumbers, lottos);
    }

    private EnumMap<Rank, Integer> calculateMatchRanks(final LottoWinningNumbers winningLotto, final Lottos lottos) {
        EnumMap<Rank, Integer> result = initResult();
        List<Rank> ranks = lottos.matchRanks(winningLotto);
        for (Rank rank : ranks) {
            result.put(rank, result.get(rank) + 1);
        }
        return result;
    }

    private EnumMap<Rank, Integer> initResult() {
        EnumMap<Rank, Integer> ranks = new EnumMap<>(Rank.class);
        for (Rank rank : Rank.values()) {
            ranks.put(rank, 0);
        }
        return ranks;
    }

    public int getRankCount(Rank rank) {
        return result.get(rank);
    }

    public int getWinnigMoney() {
        return result.keySet()
                .stream()
                .mapToInt(rank -> rank.getMoney() * result.get(rank))
                .sum();
    }
}