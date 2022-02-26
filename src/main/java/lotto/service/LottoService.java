package lotto.service;

import lotto.domain.generator.LottoGenerator;
import lotto.domain.lottonumber.Lotto;
import lotto.domain.lottonumber.WinningNumbers;
import lotto.domain.matchkind.LottoMatchKind;
import lotto.domain.purchaseamount.PurchaseAmount;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class LottoService {
    private static final int LOTTO_PRICE = 1000;
    private static final int INITIAL_MATCH_COUNT = 0;

    private final List<Lotto> lottos;
    private final PurchaseAmount purchaseAmount;
    private final Map<LottoMatchKind, Integer> matchResult;

    public LottoService(final LottoGenerator lottoGenerator, final String purchaseAmount) {
        this.purchaseAmount = PurchaseAmount.fromPurchaseAmountAndLottoPrice(purchaseAmount, LOTTO_PRICE);
        lottos = lottoGenerator.generateLottos(this.purchaseAmount.getCountOfLottoNumbers(LOTTO_PRICE));
        matchResult = new EnumMap<>(LottoMatchKind.class);
        initializeResult(matchResult);
    }

    private void initializeResult(final Map<LottoMatchKind, Integer> result) {
        for (LottoMatchKind matchKind : LottoMatchKind.values()) {
            result.put(matchKind, INITIAL_MATCH_COUNT);
        }
    }

    public int getCountOfLottoNumbers() {
        return purchaseAmount.getCountOfLottoNumbers(LOTTO_PRICE);
    }

    public List<Lotto> getLottos() {
        return lottos;
    }

    public Map<LottoMatchKind, Integer> getMatchResult(final WinningNumbers winningNumbers) {
        return match(winningNumbers);
    }

    private Map<LottoMatchKind, Integer> match(final WinningNumbers winningNumbers) {
        lottos.stream()
                .map(winningNumbers::getLottoMatchResult)
                .filter(lottoMatchKind -> lottoMatchKind != LottoMatchKind.LOWER_THAN_THREE)
                .forEach(lottoMatchKind -> matchResult.put(lottoMatchKind, matchResult.get(lottoMatchKind) + 1));
        return matchResult;
    }

    public double getProfitRate() {
        final long totalProfit = matchResult.keySet()
                .stream()
                .mapToLong(lottoMatchKind -> lottoMatchKind.getProfit(matchResult.get(lottoMatchKind)))
                .sum();
        return purchaseAmount.getProfitRate(totalProfit);
    }
}
