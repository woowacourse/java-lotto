package lotto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WinningLotto {
    private final WinningNumbers winningNumbers;
    private final int bonusNumber;

    public WinningLotto(final WinningNumbers winningNumbers, final int bonusNumber) {
        validateBonusNumberDuplicated(winningNumbers, bonusNumber);
        this.winningNumbers = winningNumbers;
        this.bonusNumber = bonusNumber;
    }

    public WinningStatistics calculateStatistics(final List<Lotto> lottos) {
        Map<Rank, Integer> statistics = new HashMap<>();
        for (final Rank rank : Rank.values()) {
            statistics.put(rank, 0);
        }

        for (final Lotto lotto : lottos) {
            Rank rank = checkRank(lotto.getNumbers(), winningNumbers.getWinningNumbers(), bonusNumber);
            statistics.put(rank, statistics.get(rank) + 1);
        }
        return new WinningStatistics(statistics);
    }

    private Rank checkRank(final List<Integer> lottoNumbers, final List<Integer> winningNumbers,
                           final int bonusNumber) {
        int matchCount = calculateMatchCount(lottoNumbers, winningNumbers);
        boolean hasBonusNumber = lottoNumbers.contains(bonusNumber);
        return Rank.checkRank(matchCount, hasBonusNumber);
    }

    private int calculateMatchCount(final List<Integer> lottoNumbers, final List<Integer> winningNumbers) {
        List<Integer> matchNumbers = new ArrayList<>(winningNumbers);
        matchNumbers.retainAll(lottoNumbers);
        int matchCount = matchNumbers.size();
        return matchCount;
    }

    private void validateBonusNumberDuplicated(final WinningNumbers winningNumbers, final int bonusNumber) {
        if (winningNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException("당첨 번호와 보너스 번호는 중복될 수 없습니다.");
        }
    }
}
