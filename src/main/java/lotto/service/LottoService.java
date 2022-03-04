package lotto.service;

import lotto.domain.*;
import lotto.domain.generator.LottoGenerator;
import lotto.domain.vo.LottoNumber;

import java.util.*;

public class LottoService {
    private static final int LOTTO_PRICE = 1000;
    private static final int INITIAL_MATCH_COUNT = 0;

    private final LottoGenerator lottoRandomGenerator;
    private final LottoGenerator lottoManualGenerator;

    public LottoService(final LottoGenerator lottoRandomGenerator, final LottoGenerator lottoManualGenerator) {
        this.lottoRandomGenerator = lottoRandomGenerator;
        this.lottoManualGenerator = lottoManualGenerator;
    }

    public int countOfLottoNumbers(final String amount) {
        return PurchaseAmount.fromPurchaseAmountAndLottoPrice(amount, LOTTO_PRICE).getCountOfLottoNumbers(LOTTO_PRICE);
    }

    public int countOfManualLottoNumbers(final String manualCounts, final int allCounts) {
        final ManualPurchaseCounts manualPurchaseCounts =
                new ManualPurchaseCounts(manualCounts, allCounts);
        return manualPurchaseCounts.getManualLottoCounts();
    }

    public List<LottoNumbers> generateLottoNumbersGroup(
            final int allCounts, final List<List<String>> manualLottoNumbersGroup) {
        final List<LottoNumbers> lottoNumbersGroup = new ArrayList<>();
        lottoNumbersGroup.addAll(generateManualLottoCounts(manualLottoNumbersGroup));
        lottoNumbersGroup.addAll(generateAutoLottoNumbers(allCounts, lottoNumbersGroup.size()));
        return lottoNumbersGroup;
    }

    private List<LottoNumbers> generateManualLottoCounts(final List<List<String>> manualLottoNumbersGroup) {
        final int manualLottoCounts = manualLottoNumbersGroup.size();
        return lottoManualGenerator.generateLottoNumbersGroup(manualLottoCounts, manualLottoNumbersGroup);
    }

    private List<LottoNumbers> generateAutoLottoNumbers(final int allCounts, final int lottoManualNumbersCount) {
        final int autoLottoCounts = allCounts - lottoManualNumbersCount;
        return lottoRandomGenerator.generateLottoNumbersGroup(
                autoLottoCounts, Collections.emptyList());
    }

    public WinningNumbers generateWinningNumbers(
            final List<String> inputLastWeekWinningNumbers, final String inputBonusNumber) {
        final LottoNumbers lastWinningNumbers = new LottoNumbers(inputLastWeekWinningNumbers);
        final LottoNumber bonusNumber = LottoNumber.from(inputBonusNumber);
        return new WinningNumbers(lastWinningNumbers, bonusNumber);
    }

    public Map<LottoMatchKind, Integer> getMatchResult(
            final List<LottoNumbers> lottoNumbersGroup, final WinningNumbers winningNumbers) {
        final Map<LottoMatchKind, Integer> matchResult = new EnumMap<>(LottoMatchKind.class);
        initializeResult(matchResult);
        lottoNumbersGroup.stream()
                .map(winningNumbers::getLottoMatchResult)
                .filter(lottoMatchKind -> lottoMatchKind != LottoMatchKind.BLANK)
                .forEach(lottoMatchKind -> matchResult.put(lottoMatchKind, matchResult.get(lottoMatchKind) + 1));
        return matchResult;
    }

    private void initializeResult(final Map<LottoMatchKind, Integer> result) {
        Arrays.stream(LottoMatchKind.values())
                .forEach(lottoMatchKind -> result.put(lottoMatchKind, INITIAL_MATCH_COUNT));
    }

    public double getProfitRate(final Map<LottoMatchKind, Integer> matchResult, final int allCounts) {
        final long totalProfit = matchResult.keySet()
                .stream()
                .mapToLong(lottoMatchKind -> lottoMatchKind.getProfit(matchResult.get(lottoMatchKind)))
                .sum();
        return (double) totalProfit / (allCounts * LOTTO_PRICE);
    }
}
