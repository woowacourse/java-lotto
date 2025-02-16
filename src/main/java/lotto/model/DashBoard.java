package lotto.model;

import static lotto.LottoConstants.Price.LOTTO_PRICE_UNIT;

import java.util.EnumMap;
import java.util.Map.Entry;
import java.util.Objects;
import lotto.Rank;

public class DashBoard {

    private static final int INITIAL_RANK_COUNT = 0;
    private static final int RANK_COUNT_INCREMENT = 1;

    private final EnumMap<Rank, Integer> ranks;

    public DashBoard() {
        ranks = new EnumMap<>(Rank.class);
        initializeRanks();
    }

    private void initializeRanks() {
        for (Rank rank : Rank.values()) {
            ranks.put(rank, INITIAL_RANK_COUNT);
        }
    }

    public void recordWinningResults(WinningLotto winningLotto, LottoTicket lottoTicket) {
        validateRecordRequest(winningLotto, lottoTicket);
        for (Lotto lotto : lottoTicket.getLottos()) {
            Rank rank = winningLotto.determineRank(lotto);
            recordWinningRank(rank);
        }
    }

    private void validateRecordRequest(WinningLotto winningLotto, LottoTicket lottoTicket) {
        Objects.requireNonNull(winningLotto, "당첨 번호는 null이 될 수 없습니다.");
        Objects.requireNonNull(lottoTicket, "로또 티켓은 null이 될 수 없습니다.");
    }

    public void recordWinningRank(Rank rank) {
        Objects.requireNonNull(rank, "당첨 결과는 null이 될 수 없습니다.");
        incrementRankCount(rank);
    }

    /**
     * rank 키가 존재하지 않으면 RANK_COUNT_INCREMENT로 초기화하고, 존재하면 기존 값에 RANK_COUNT_INCREMENT를 더하여 업데이트한다.
     */
    private void incrementRankCount(Rank rank) {
        ranks.merge(rank, RANK_COUNT_INCREMENT, Integer::sum);
    }

    public float calculateWinningRate() {
        boolean isNoLottoPurchased = getTotalPurchasedLottoCount() == 0;
        if (isNoLottoPurchased) {
            throw new IllegalArgumentException("로또를 구입한 내역이 없어서 수익률을 계산할 수 없습니다.");
        }
        int purchaseAmount = getTotalPurchasedLottoCount() * LOTTO_PRICE_UNIT;
        return (float) calculateTotalWinningAmount() / purchaseAmount;
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
