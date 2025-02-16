package lotto.model;

import static lotto.rule.LottoConstants.Price.LOTTO_PRICE_UNIT;

import java.util.EnumMap;
import java.util.Map.Entry;
import java.util.Objects;
import lotto.rule.Rank;

public class DashBoard {

    private static final int INITIAL_RANK_COUNT = 0;
    private static final int RANK_COUNT_INCREMENT = 1;

    private final EnumMap<Rank, Integer> ranks;

    public DashBoard() {
        this.ranks = initializeRanks();
    }

    private EnumMap<Rank, Integer> initializeRanks() {
        EnumMap<Rank, Integer> rankMap = new EnumMap<>(Rank.class);
        for (Rank rank : Rank.values()) {
            rankMap.put(rank, INITIAL_RANK_COUNT);
        }
        return rankMap;
    }

    public void recordWinningResults(WinningLotto winningLotto, LottoTicket lottoTicket) {
        validateRecordRequest(winningLotto, lottoTicket);
        for (Lotto lotto : lottoTicket.lottos()) {
            Rank rank = winningLotto.determineRank(lotto);
            incrementWinningCount(rank);
        }
    }

    private void validateRecordRequest(WinningLotto winningLotto, LottoTicket lottoTicket) {
        Objects.requireNonNull(winningLotto, "당첨 번호는 null이 될 수 없습니다.");
        Objects.requireNonNull(lottoTicket, "로또 티켓은 null이 될 수 없습니다.");
    }

    /**
     * rank 키가 존재하지 않으면 RANK_COUNT_INCREMENT로 초기화하고, 존재하면 기존 값에 RANK_COUNT_INCREMENT를 더하여 업데이트한다.
     */
    public void incrementWinningCount(Rank rank) {
        Objects.requireNonNull(rank, "당첨 결과는 null이 될 수 없습니다.");
        ranks.merge(rank, RANK_COUNT_INCREMENT, Integer::sum);
    }

    public float calculateWinningRate() {
        validateWinningRateCalculation();
        int purchaseAmount = getTotalPurchasedLottoCount() * LOTTO_PRICE_UNIT;
        return (float) calculateTotalWinningAmount() / purchaseAmount;
    }

    private void validateWinningRateCalculation() {
        boolean isNoLottoPurchased = getTotalPurchasedLottoCount() == 0;
        if (isNoLottoPurchased) {
            throw new IllegalArgumentException("로또를 구입한 내역이 없어서 수익률을 계산할 수 없습니다.");
        }
    }

    private int getTotalPurchasedLottoCount() {
        return ranks.values()
                .stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    private int calculateTotalWinningAmount() {
        return ranks.entrySet().stream()
                .mapToInt(this::getTotalPrizeAmountForRank)
                .sum();
    }

    private int getTotalPrizeAmountForRank(Entry<Rank, Integer> rankCountEntry) {
        int winningAmount = rankCountEntry.getKey().getWinningAmount();
        Integer count = rankCountEntry.getValue();
        return winningAmount * count;
    }

    public int getWinningLottoCountForRank(Rank rank) {
        return ranks.get(rank);
    }

    public EnumMap<Rank, Integer> getRanks() {
        return new EnumMap<>(ranks);
    }
}
