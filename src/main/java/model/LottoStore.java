package model;

import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

public class LottoStore {

    private static final int LOTTO_PRICE = 1_000;

    private final LottoNumberGenerator lottoNumberGenerator;
    private final LottoRankCalculator lottoRankCalculator;

    public LottoStore(LottoNumberGenerator lottoNumberGenerator, LottoRankCalculator lottoRankCalculator) {
        this.lottoNumberGenerator = lottoNumberGenerator;
        this.lottoRankCalculator = lottoRankCalculator;
    }

    public int calculatePurchaseCount(int purchaseAmount) {
        validateAmountUnit(purchaseAmount);
        return purchaseAmount / LOTTO_PRICE;
    }

    public List<LottoTicket> createLottoTickets(int purchaseCount) {
        return IntStream.range(0, purchaseCount)
                .mapToObj(count -> new LottoTicket(lottoNumberGenerator.generate()))
                .toList();
    }

    public LottoRankResult calculateRankMatchCount(List<LottoTicket> lottoTickets, WinningLotto winningLotto) {
        List<LottoRank> lottoRanks = calculateRank(lottoTickets, winningLotto);
        LottoRankResult lottoRankResult = new LottoRankResult();
        for (LottoRank lottoRank : lottoRanks) {
            lottoRankResult.updateRankCount(lottoRank);
        }
        return lottoRankResult;
    }

    public double calculateProfitRate(int lottoTicketCount, LottoRankResult lottoRankResult) {
        int purchasedAmount = lottoTicketCount * LOTTO_PRICE;
        int profit = lottoRankResult.getKeys().stream()
                .mapToInt(rank -> rank.getWinningAmount() * lottoRankResult.getValue(rank)).sum();
        return (double) profit / purchasedAmount;
    }

    private List<LottoRank> calculateRank(List<LottoTicket> lottoTickets, WinningLotto winningLotto) {
        return lottoTickets.stream().map(lottoTicket -> lottoRankCalculator.calculate(lottoTicket, winningLotto))
                .filter(Objects::nonNull).toList();
    }

    private void validateAmountUnit(int purchaseAmount) {
        if (purchaseAmount % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException("[ERROR] 구입 금액은 1000원 단위여야 합니다.");
        }
    }
}
