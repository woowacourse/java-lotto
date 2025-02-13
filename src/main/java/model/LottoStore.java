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

    public List<LottoTicket> purchase(final int paidAmount) {
        validateAmountUnit(paidAmount);

        int purchasedTicketAmount = paidAmount / LOTTO_PRICE;
        return IntStream.range(0, purchasedTicketAmount)
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
        int paidAmount = lottoTicketCount * LOTTO_PRICE;
        int profit = lottoRankResult.getRanks().stream()
                .mapToInt(rank -> rank.getPrizeMoney() * lottoRankResult.getCountByRank(rank)).sum();
        return (double) profit / paidAmount;
    }

    private List<LottoRank> calculateRank(List<LottoTicket> lottoTickets, WinningLotto winningLotto) {
        return lottoTickets.stream().map(lottoTicket -> lottoRankCalculator.calculate(lottoTicket, winningLotto))
                .filter(Objects::nonNull).toList();
    }

    private void validateAmountUnit(int paidAmount) {
        if (paidAmount % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException("[ERROR] 구입 금액은 1000원 단위여야 합니다.");
        }
    }
}
