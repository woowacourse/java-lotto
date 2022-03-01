package lotto.service;

import lotto.domain.*;
import lotto.domain.generator.LottoGenerator;
import lotto.domain.vo.LottoNumber;

import java.util.*;
import java.util.stream.Collectors;

public class LottoService {
    private static final String DUPLICATE_MANUAL_EXCEPTION_MESSAGE = "완전히 동일한 줄이 존재합니다.";

    private static final int LOTTO_PRICE = 1000;
    private static final int INITIAL_MATCH_COUNT = 0;

    private final LottoGenerator lottoGenerator;
    private final List<LottoNumbers> lottoNumbersGroup;

    public LottoService(final LottoGenerator lottoGenerator) {
        this.lottoGenerator = lottoGenerator;
        lottoNumbersGroup = new ArrayList<>();
    }

    public int countOfLottoNumbers(final String amount) {
        return PurchaseAmount.fromPurchaseAmountAndLottoPrice(amount, LOTTO_PRICE).getCountOfLottoNumbers(LOTTO_PRICE);
    }

    public int countOfManualLottoNumbers(final String manualCounts, final int allCounts) {
        final ManualPurchaseCounts manualPurchaseCounts =
                new ManualPurchaseCounts(manualCounts, allCounts);
        return manualPurchaseCounts.getManualLottoCounts();
    }

    public int generateManualLottoCounts(final List<List<String>> manualLottoNumbersGroup) {
        final Set<LottoNumbers> manualNumbersGroup = manualLottoNumbersGroup.stream()
                .map(LottoNumbers::new)
                .collect(Collectors.toUnmodifiableSet());
        validateDuplicateManualNumbersGroup(manualLottoNumbersGroup, manualNumbersGroup);
        lottoNumbersGroup.addAll(manualNumbersGroup);
        return lottoNumbersGroup.size();
    }

    private void validateDuplicateManualNumbersGroup(List<List<String>> manualLottoNumbersGroup, Set<LottoNumbers> manualNumbersGroup) {
        if (manualNumbersGroup.size() != manualLottoNumbersGroup.size()) {
            throw new IllegalArgumentException(DUPLICATE_MANUAL_EXCEPTION_MESSAGE);
        }
    }

    public void generateAutoLottoNumbers(final int lottoNumbersCount) {
        final int autoLottoCounts = lottoNumbersCount - lottoNumbersGroup.size();
        lottoNumbersGroup.addAll(lottoGenerator.generateLottoNumbersGroup(autoLottoCounts));
    }

    public List<LottoNumbers> getLottoNumbersGroup() {
        return lottoNumbersGroup;
    }

    public WinningNumbers generateWinningNumbers(List<String> inputLastWeekWinningNumbers, String inputBonusNumber) {
        final LottoNumbers lastWinningNumbers = new LottoNumbers(inputLastWeekWinningNumbers);
        final LottoNumber bonusNumber = LottoNumber.from(inputBonusNumber);
        return new WinningNumbers(lastWinningNumbers, bonusNumber);
    }

    public Map<LottoMatchKind, Integer> getMatchResult(final WinningNumbers winningNumbers) {
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
