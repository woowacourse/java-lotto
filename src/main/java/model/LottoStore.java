package model;

import java.util.List;
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

    public void calculateRank(LottoTicket lottoTicket, WinningLotto winningLotto) {
        lottoRankCalculator.calculate(lottoTicket, winningLotto);
    }

    private void validateAmountUnit(int purchaseAmount) {
        if (purchaseAmount % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException("[ERROR] 구입 금액은 1000원 단위여야 합니다.");
        }
    }
}
