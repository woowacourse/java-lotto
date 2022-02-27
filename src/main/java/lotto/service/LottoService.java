package lotto.service;

import lotto.domain.*;
import lotto.domain.generator.LottoGenerator;
import lotto.domain.vo.LottoNumber;

import java.util.*;
import java.util.stream.Collectors;

public class LottoService {
    private static final int LOTTO_PRICE = 1000;
    private static final int INITIAL_MATCH_COUNT = 0;

    private List<LottoNumbers> lottoNumbersGroup;
    private final LottoGenerator lottoGenerator;
    private final PurchaseAmount purchaseAmount;
    private final Map<LottoMatchKind, Integer> matchResult;

    public LottoService(final LottoGenerator lottoGenerator, final String purchaseAmount) {
        this.lottoGenerator = lottoGenerator;
        this.purchaseAmount = PurchaseAmount.fromPurchaseAmountAndLottoPrice(purchaseAmount, LOTTO_PRICE);
        lottoNumbersGroup= new ArrayList<>();
        matchResult = new EnumMap<>(LottoMatchKind.class);
        initializeResult(matchResult);
    }

    public int countOfManualLottoNumbers(final String manualCounts) {
        ManualPurchaseCounts manualPurchaseCounts =
                new ManualPurchaseCounts(manualCounts, purchaseAmount.getCountOfLottoNumbers(LOTTO_PRICE));
        return manualPurchaseCounts.getManualLottoCounts();
    }

    public int generateManualLottoCounts(final List<List<String>> manualLottoNumbersGroup) {
        final List<LottoNumbers> manualNumbersGroup = manualLottoNumbersGroup.stream()
                .map(LottoNumbers::new)
                .collect(Collectors.toUnmodifiableList());
        lottoNumbersGroup.addAll(manualNumbersGroup);
        return lottoNumbersGroup.size();
    }

    public void generateAutoLottoNumbers() {
        final int autoLottoCounts = this.purchaseAmount.getCountOfLottoNumbers(LOTTO_PRICE) - lottoNumbersGroup.size();
        lottoNumbersGroup.addAll(lottoGenerator.generateLottoNumbersGroup(autoLottoCounts));
    }

    private void initializeResult(final Map<LottoMatchKind, Integer> result) {
        Arrays.stream(LottoMatchKind.values())
                .forEach(lottoMatchKind -> result.put(lottoMatchKind, INITIAL_MATCH_COUNT));
    }

    public int countOfLottoNumbers() {
        return purchaseAmount.getCountOfLottoNumbers(LOTTO_PRICE);
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
        return match(winningNumbers);
    }

    private Map<LottoMatchKind, Integer> match(final WinningNumbers winningNumbers) {
        lottoNumbersGroup.stream()
                .map(winningNumbers::getLottoMatchResult)
                .filter(lottoMatchKind -> lottoMatchKind != LottoMatchKind.BLANK)
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
