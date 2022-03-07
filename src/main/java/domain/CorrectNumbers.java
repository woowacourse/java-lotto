package domain;

import java.util.*;

public class CorrectNumbers {

    private static final int RANK_COUNT_INIT_NUMBER = 0;
    private static final int RANK_COUNT_UNIT = 1;

    private final List<CorrectNumber> correctNumbers = new ArrayList<>();

    public CorrectNumbers(IssuedLotto issuedLotto, Lotto lastWinLotto, LottoNumber bonusNumber) {
        for (final Lotto lotto : issuedLotto.getIssuedLotto()) {
            correctNumbers.add(CorrectNumber.getCorrectNumber(lotto.compare(lastWinLotto), lotto.isContainNumber(bonusNumber)));
        }
    }

    public SortedMap<RankPrize, Integer> getRankCounts() {
        SortedMap<RankPrize, Integer> rankCount = new TreeMap<>(Collections.reverseOrder());
        initRank(rankCount);
        countRank(rankCount);
        return rankCount;
    }

    private void initRank(final SortedMap<RankPrize, Integer> rankCount) {
        Arrays.stream(RankPrize.values())
                .forEach(e -> {
                    rankCount.put(e, RANK_COUNT_INIT_NUMBER);
                });
    }

    private void countRank(final SortedMap<RankPrize, Integer> rankCount) {
        for (CorrectNumber correctNumber : correctNumbers) {
            countOverFifthRank(rankCount, correctNumber);
        }
    }

    private void countOverFifthRank(final SortedMap<RankPrize, Integer> rankCount, final CorrectNumber correctNumber) {
        if (correctNumber.isInRank()) {
            final RankPrize rankPrize = correctNumber.findRankPrize();
            rankCount.put(rankPrize, rankCount.get(rankPrize) + RANK_COUNT_UNIT);
        }
    }
}
