package lotto.dto;

import java.util.EnumMap;
import lotto.domain.Lotto;
import lotto.domain.LottoWinningNumbers;
import lotto.domain.Rank;

public class Result {

    private EnumMap<Rank, Integer> result = new EnumMap<>(Rank.class);

    public Result() {
        initResult();
    }

    public void calculateWinning(final LottoWinningNumbers winningLotto, final Lotto lotto) {
        int matchCount = winningLotto.matchCount(lotto);
        boolean hasBonusNumber = lotto.contains(winningLotto.getBonusNumber());
        Rank rank = Rank.matchRank(matchCount, hasBonusNumber);
        result.put(rank, result.get(rank) + 1);
    }

    public void initResult() {
        for (Rank rank : Rank.values()) {
            result.put(rank, 0);
        }
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
