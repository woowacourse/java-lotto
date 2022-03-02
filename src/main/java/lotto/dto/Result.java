package lotto.dto;

import java.util.EnumMap;
import lotto.domain.Lotto;
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
        for (Lotto lotto : lottos.getLottos()) {
            int matchCount = winningLotto.matchCount(lotto);
            boolean hasBonusNumber = lotto.contains(winningLotto.getBonusNumber());
            Rank rank = Rank.matchRank(matchCount, hasBonusNumber);
            result.put(rank, result.get(rank) + 1);
        }
        return new EnumMap<>(result);
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