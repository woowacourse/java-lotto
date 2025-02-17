package lotto.model;

import java.util.EnumMap;

public class DashBoard {

    private static final int INITIAL_RANK_COUNT = 0;
    private static final int RANK_COUNT_INCREMENT = 1;

    private final EnumMap<Rank, Integer> ranks;

    public DashBoard() {
        ranks = initializeRanks();
    }

    private EnumMap<Rank, Integer> initializeRanks() {
        final EnumMap<Rank, Integer> ranks = new EnumMap<>(Rank.class);
        for (Rank rank : Rank.values()) {
            ranks.put(rank, INITIAL_RANK_COUNT);
        }
        return ranks;
    }

    public void recordResult(final Lotto lotto, final Lotto winningLotto, final LottoNumber bonusNumber) {
        Rank rank = RankDeterminer.determine(lotto, winningLotto, bonusNumber);
        increaseRankCount(rank);
    }

    /**
     * rank 키가 존재하지 않으면 RANK_COUNT_INCREMENT로 초기화하고,
     * 존재하면 기존 값에 RANK_COUNT_INCREMENT를 더하여 업데이트한다.
     */
    private void increaseRankCount(final Rank rank) {
        ranks.merge(rank, RANK_COUNT_INCREMENT, Integer::sum);
    }

    public EnumMap<Rank, Integer> getRanks() {
        return new EnumMap<>(ranks);
    }
}
