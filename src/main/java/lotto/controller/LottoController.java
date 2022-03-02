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
        final int manualCounts = generateManualLottoNumbersGroup(allCounts);
        lottoService.generateAutoLottoNumbers(allCounts);
        outputView.printPurchaseCount(manualCounts, allCounts);
        printLottoNumbersGroup();
        final WinningNumbers winningNumbers = generateWinningNumbers();
        printResult(winningNumbers, allCounts);
    }

    private int initializeLottoCounts() {
        try {
            String inputAmount = inputView.inputPurchaseAmount();
            return lottoService.countOfLottoNumbers(inputAmount);
        } catch (final Exception e) {
            inputView.printErrorMessage(e.getMessage());
            return initializeLottoCounts();
        }
    }

    private int generateManualLottoNumbersGroup(final int allCounts) {
        try {
            final String manualPurchaseCounts = inputView.inputManualPurchaseCounts();
            final int manualLottoCounts = lottoService.countOfManualLottoNumbers(manualPurchaseCounts, allCounts);
            lottoService.generateManualLottoCounts(inputByManualLottoNumbersGroup(manualLottoCounts));
            return manualLottoCounts;
        } catch (final Exception e) {
            inputView.printErrorMessage(e.getMessage());
            return generateManualLottoNumbersGroup(allCounts);
        }
    }

    private List<List<String>> inputByManualLottoNumbersGroup(final int manualLottoCounts) {
        if (manualLottoCounts == 0) {
            return Collections.emptyList();
        }
        return inputView.inputManualPurchaseWinningNumbers(manualLottoCounts);
    }

    private void printLottoNumbersGroup() {
        final List<LottoNumbersDto> numbersGroup =
                convertLottoNumbersGroupToDtos(lottoService.getLottoNumbersGroup());
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
            List<String> lastWeekWinningNumbers = inputView.inputLastWeekWinningNumbers();
            String bonusNumber = inputView.inputBonusNumber();
            return lottoService.generateWinningNumbers(lastWeekWinningNumbers, bonusNumber);
        } catch (final Exception e) {
            inputView.printErrorMessage(e.getMessage());
            return generateWinningNumbers();
        }
    }

    private void printResult(final WinningNumbers winningNumbers, final int allCounts) {
        final Map<LottoMatchKind, Integer> matchResult = lottoService.getMatchResult(winningNumbers);
        final List<LottoMatchKindDto> results =
                convertWinningResultsToDtos(matchResult);
        outputView.printCountOfWinningByMatchKind(results);
        outputView.printProfitRate(lottoService.getProfitRate(matchResult, allCounts));
    }

    private List<LottoMatchKindDto> convertWinningResultsToDtos(final Map<LottoMatchKind, Integer> results) {
        return results.keySet().stream()
                .filter(lottoMatchKind -> lottoMatchKind != LottoMatchKind.BLANK)
                .map(lottoMatchKind -> new LottoMatchKindDto(lottoMatchKind, results.get(lottoMatchKind)))
                .collect(Collectors.toUnmodifiableList());
    }
}
