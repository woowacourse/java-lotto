package lotto.controller;

import lotto.domain.LottoMatchKind;
import lotto.domain.LottoNumbers;
import lotto.domain.WinningNumbers;
import lotto.domain.generator.LottoGenerator;
import lotto.dto.LottoMatchKindDto;
import lotto.dto.LottoNumbersDto;
import lotto.service.LottoService;
import lotto.view.input.InputView;
import lotto.view.output.OutputView;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LottoController {
    private final LottoService lottoService;
    private final InputView inputView;
    private final OutputView outputView;

    public LottoController(
            final LottoGenerator lottoRandomGenerator, final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
        lottoService = new LottoService(lottoRandomGenerator);
    }

    public void run() {
        final int allCounts = initializeLottoCounts();
        final int manualCounts = getManualLottoCounts(allCounts);
        final List<LottoNumbers> lottoNumbersGroup = generateLottoNumbersGroup(allCounts, manualCounts);
        outputView.printPurchaseCount(manualCounts, allCounts);
        printLottoNumbersGroup(lottoNumbersGroup);
        final WinningNumbers winningNumbers = generateWinningNumbers();
        printResult(winningNumbers, lottoNumbersGroup);
    }

    private int initializeLottoCounts() {
        try {
            final String inputAmount = inputView.inputPurchaseAmount();
            return lottoService.countOfLottoNumbers(inputAmount);
        } catch (final Exception e) {
            inputView.printErrorMessage(e.getMessage());
            return initializeLottoCounts();
        }
    }

    private List<LottoNumbers> generateLottoNumbersGroup(final int allCounts, final int manualCounts) {
        try {
            return lottoService.generateLottoNumbersGroup(allCounts, inputByManualLottoNumbersGroup(manualCounts));
        } catch (final Exception e) {
            inputView.printErrorMessage(e.getMessage());
            return generateLottoNumbersGroup(allCounts, manualCounts);
        }
    }

    private int getManualLottoCounts(int allCounts) {
        try {
            final String manualPurchaseCounts = inputView.inputManualPurchaseCounts();
            return lottoService.countOfManualLottoNumbers(manualPurchaseCounts, allCounts);
        } catch (final Exception e) {
            inputView.printErrorMessage(e.getMessage());
            return getManualLottoCounts(allCounts);
        }
    }

    private List<List<String>> inputByManualLottoNumbersGroup(final int manualLottoCounts) {
        if (manualLottoCounts == 0) {
            return Collections.emptyList();
        }
        return inputView.inputManualPurchaseWinningNumbers(manualLottoCounts);
    }

    private void printLottoNumbersGroup(final List<LottoNumbers> lottoNumbersGroup) {
        final List<LottoNumbersDto> numbersGroup =
                convertLottoNumbersGroupToDtos(lottoNumbersGroup);
        outputView.printLottoNumbersGroup(numbersGroup);
    }

    private List<LottoNumbersDto> convertLottoNumbersGroupToDtos(final List<LottoNumbers> numbersGroup) {
        return numbersGroup.stream()
                .map(LottoNumbers::getValues)
                .map(LottoNumbersDto::new)
                .collect(Collectors.toUnmodifiableList());
    }

    private WinningNumbers generateWinningNumbers() {
        try {
            final List<String> lastWeekWinningNumbers = inputView.inputLastWeekWinningNumbers();
            final String bonusNumber = inputView.inputBonusNumber();
            return lottoService.generateWinningNumbers(lastWeekWinningNumbers, bonusNumber);
        } catch (final Exception e) {
            inputView.printErrorMessage(e.getMessage());
            return generateWinningNumbers();
        }
    }

    private void printResult(final WinningNumbers winningNumbers, final List<LottoNumbers> lottoNumbersGroup) {
        final Map<LottoMatchKind, Integer> matchResult = lottoService.getMatchResult(lottoNumbersGroup, winningNumbers);
        final List<LottoMatchKindDto> results =
                convertWinningResultsToDtos(matchResult);
        outputView.printCountOfWinningByMatchKind(results);
        outputView.printProfitRate(lottoService.getProfitRate(matchResult, lottoNumbersGroup.size()));
    }

    private List<LottoMatchKindDto> convertWinningResultsToDtos(final Map<LottoMatchKind, Integer> results) {
        return results.keySet().stream()
                .filter(lottoMatchKind -> lottoMatchKind != LottoMatchKind.BLANK)
                .map(lottoMatchKind -> new LottoMatchKindDto(lottoMatchKind, results.get(lottoMatchKind)))
                .collect(Collectors.toUnmodifiableList());
    }
}
