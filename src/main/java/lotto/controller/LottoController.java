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

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LottoController {
    private final LottoService lottoService;
    private final InputView inputView;
    private final OutputView outputView;

    public LottoController(final LottoGenerator lottoGenerator, final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
        lottoService = initializeLottoService(lottoGenerator);
    }

    private LottoService initializeLottoService(final LottoGenerator lottoGenerator) {
        try {
            final String purchaseAmountInput = inputView.inputPurchaseAmount();
            final String manualPurchaseCounts = inputView.inputManualPurchaseCounts();
            return new LottoService(lottoGenerator, purchaseAmountInput, manualPurchaseCounts);
        } catch (final Exception e) {
            inputView.printErrorMessage(e.getMessage());
            return initializeLottoService(lottoGenerator);
        }
    }

    public void run() {
        generateManualLottoNumbersGroup();
        generateAutoLottoNumbersGroup();
        outputView.printPurchaseCount(lottoService.getCountOfManualLottoNumbers(), lottoService.getCountOfLottoNumbers());
        printLottoNumbersGroup();
        final WinningNumbers winningNumbers = generateWinningNumbers();
        printResult(winningNumbers);
    }

    private void generateManualLottoNumbersGroup() {
        try {
            final int manualLottoCounts = lottoService.getCountOfManualLottoNumbers();
            List<List<String>> manualLottoNumbersGroup = inputByManualLottoNumbersGroup(manualLottoCounts);
            lottoService.generateManualLottoCounts(manualLottoNumbersGroup);
        } catch (final Exception e) {
            inputView.printErrorMessage(e.getMessage());
            generateManualLottoNumbersGroup();
        }
    }

    private List<List<String>> inputByManualLottoNumbersGroup(int manualLottoCounts) {
        if (manualLottoCounts == 0) {
            return Arrays.asList();
        }
        return inputView.inputManualPurchaseWinningNumbers(manualLottoCounts);
    }

    private void generateAutoLottoNumbersGroup() {
        lottoService.generateAutoLottoNumbers();
    }

    private void printLottoNumbersGroup() {
        final List<LottoNumbersDto> numbersGroup =
                convertLottoNumbersGroupToDtos(lottoService.getLottoNumbersGroup());
        outputView.printLottoNumbersGroup(numbersGroup);
    }

    private List<LottoNumbersDto> convertLottoNumbersGroupToDtos(
            final List<LottoNumbers> numbersGroup) {
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

    private void printResult(WinningNumbers winningNumbers) {
        final List<LottoMatchKindDto> results =
                convertWinningResultsToDtos(lottoService.getMatchResult(winningNumbers));
        outputView.printCountOfWinningByMatchKind(results);
        outputView.printProfitRate(lottoService.getProfitRate());
    }

    private List<LottoMatchKindDto> convertWinningResultsToDtos(final Map<LottoMatchKind, Integer> results) {
        return results.keySet().stream()
                .map(lottoMatchKind -> new LottoMatchKindDto(lottoMatchKind, results.get(lottoMatchKind)))
                .collect(Collectors.toUnmodifiableList());
    }
}
