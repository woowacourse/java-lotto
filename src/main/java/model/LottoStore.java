package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    public List<LottoTicket> purchase(final int purchaseAmount) {
        validateAmountUnit(purchaseAmount);
        int purchaseCount = purchaseAmount / LOTTO_PRICE;
        return IntStream.range(0, purchaseCount)
                .mapToObj(count -> new LottoTicket(lottoNumberGenerator.generate()))
                .toList();
    }

    public Map<LottoRank, Integer> calculateRankMatchCount(List<LottoTicket> lottoTickets, WinningLotto winningLotto) {
        List<LottoRank> lottoRanks = calculateRank(lottoTickets, winningLotto);
        Map<LottoRank, Integer> rankCount = new HashMap<>();
        for (LottoRank rank : LottoRank.values()) {
            rankCount.put(rank, 0);
        }

        for (LottoRank lottoRank : lottoRanks) {
            rankCount.merge(lottoRank, 1, Integer::sum);
        }
        return rankCount;
    }

    private List<LottoRank> calculateRank(List<LottoTicket> lottoTickets, WinningLotto winningLotto) {
        return lottoTickets.stream()
                .map(lottoTicket -> lottoRankCalculator.calculate(lottoTicket, winningLotto))
                .filter(Objects::nonNull)
                .toList();
    }

    private void validateAmountUnit(int purchaseAmount) {
        if (purchaseAmount % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException("[ERROR] 구입 금액은 1000원 단위여야 합니다.");
        }
    }
}
