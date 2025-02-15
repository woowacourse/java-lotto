package lotto.model;

import static lotto.LottoConstants.Price.LOTTO_PRICE_UNIT;

import java.util.EnumMap;
import java.util.Map.Entry;
import lotto.Rank;

public class DashBoard {

    private static final int INITIAL_RANK_COUNT = 0;
    private static final int RANK_COUNT_INCREMENT = 1;

    private final EnumMap<Rank, Integer> ranks;

    public DashBoard() {
        ranks = initializeRanks();
    }

    private EnumMap<Rank, Integer> initializeRanks() {
        EnumMap<Rank, Integer> ranks = new EnumMap<>(Rank.class);
        for (Rank rank : Rank.values()) {
            ranks.put(rank, INITIAL_RANK_COUNT);
        }
        return ranks;
    }

    public void recordResult(Rank rank) {
        validateRank(rank);
        increaseRankCount(rank);
    }

    private void validateRank(Rank rank) {
        if (rank == null) {
            throw new IllegalArgumentException("당첨 결과는 null이 될 수 없습니다.");
        }
    }

    /**
     * rank 키가 존재하지 않으면 RANK_COUNT_INCREMENT로 초기화하고, 존재하면 기존 값에 RANK_COUNT_INCREMENT를 더하여 업데이트한다.
     */
    private void increaseRankCount(Rank rank) {
        ranks.merge(rank, RANK_COUNT_INCREMENT, Integer::sum);
    }

    public float calculateWinningRate(int purchaseAmount) {
        validateAmount(purchaseAmount);
        return (float) calculateTotalWinningAmount() / purchaseAmount;
    }

    private void validateAmount(int amount) {
        if (isZeroAmount(amount)) {
            throw new IllegalArgumentException("수익률을 계산할 때, 구입 금액은 최소 %,d원 이상이어야 합니다.".formatted(LOTTO_PRICE_UNIT));
        }
        if (isNonDivisibleByUnit(amount)) {
            throw new IllegalArgumentException("수익률을 계산할 때, 구입 금액은 %,d원 단위만 가능합니다.".formatted(LOTTO_PRICE_UNIT));
        }
    }

    private boolean isZeroAmount(int amount) {
        return amount == 0;
    }

    private boolean isNonDivisibleByUnit(int amount) {
        return amount % LOTTO_PRICE_UNIT != 0;
    }

    private int calculateTotalWinningAmount() {
        return ranks.entrySet().stream()
                .mapToInt(this::calculateWinningAmountPerRank)
                .sum();
    }

    private int calculateWinningAmountPerRank(Entry<Rank, Integer> entry) {
        int winningAmount = entry.getKey().getWinningAmount();
        Integer count = entry.getValue();
        return winningAmount * count;
    }

    public int getWinningCount(Rank rank) {
        return ranks.get(rank);
    }

    public EnumMap<Rank, Integer> getRanks() {
        return new EnumMap<>(ranks);
    }
}
