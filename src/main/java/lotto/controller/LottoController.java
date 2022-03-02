package lotto.controller;

import lotto.domain.generator.LottoGenerator;
import lotto.domain.lottonumber.Lotto;
import lotto.domain.lottonumber.Lottos;
import lotto.domain.lottonumber.WinningNumbers;
import lotto.domain.lottonumber.vo.LottoNumber;
import lotto.domain.matchkind.LottoMatchKind;
import lotto.domain.purchaseamount.ManualPurchaseCount;
import lotto.domain.purchaseamount.PurchaseAmount;
import lotto.domain.winningresult.WinningResult;
import lotto.dto.InputLottoDto;
import lotto.dto.LottoMatchKindDto;
import lotto.dto.LottoNumbersDto;
import lotto.service.LottoService;
import lotto.view.input.InputView;
import lotto.view.output.OutputView;

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
        final PurchaseAmount totalPurchaseAmount = inputTotalPurchaseAmount();
        final int manualPurchaseAmount = inputManualPurchaseAmount(totalPurchaseAmount);
        final List<Lotto> manualLottos = inputManualLottos(manualPurchaseAmount);
        return new LottoService(lottoGenerator, totalPurchaseAmount, manualLottos);
    }

    private PurchaseAmount inputTotalPurchaseAmount() {
        try {
            final String purchaseAmountInput = inputView.inputPurchaseAmount();
            return new PurchaseAmount(purchaseAmountInput);
        } catch (final Exception e) {
            inputView.printErrorMessage(e.getMessage());
            return inputTotalPurchaseAmount();
        }
    }

    private int inputManualPurchaseAmount(final PurchaseAmount totalPurchaseAmount) {
        try {
            final String manualPurchaseLottoAmount = inputView.inputManualPurchaseAmount();
            final ManualPurchaseCount manualPurchaseCount = new ManualPurchaseCount(manualPurchaseLottoAmount, totalPurchaseAmount);
            return manualPurchaseCount.getValue();
        } catch (final Exception e) {
            inputView.printErrorMessage(e.getMessage());
            return inputManualPurchaseAmount(totalPurchaseAmount);
        }
    }

    private List<Lotto> inputManualLottos(final int numberOfManualLottos) {
        try {
            final List<InputLottoDto> manualInputLottos = inputView.inputManualLottoNumbers(numberOfManualLottos);
            final List<Lotto> manual = manualInputLottos.stream()
                    .map(InputLottoDto::getNumbers)
                    .map(Lotto::new)
                    .collect(Collectors.toUnmodifiableList());
            return new Lottos(manual).getLottos();
        } catch (final Exception e) {
            inputView.printErrorMessage(e.getMessage());
            return inputManualLottos(numberOfManualLottos);
        }
    }

    public void run() {
        outputView.printPurchaseCount(lottoService.getCountOfLottoNumbers());
        printLottoNumbersGroup();
        final WinningNumbers winningNumbers = generateWinningNumbers();
        printResult(winningNumbers);
    }

    private void printLottoNumbersGroup() {
        final List<LottoNumbersDto> numbersGroup =
                convertLottoNumbersGroupToDtos(lottoService.getLottos());
        outputView.printLottoNumbersGroup(numbersGroup);
    }

    private List<LottoNumbersDto> convertLottoNumbersGroupToDtos(final List<Lotto> numbersGroup) {
        return numbersGroup.stream()
                .map(Lotto::getValues)
                .map(LottoNumbersDto::new)
                .collect(Collectors.toUnmodifiableList());
    }

    private WinningNumbers generateWinningNumbers() {
        try {
            final Lotto lastWinningNumbers = new Lotto(inputView.inputLastWeekWinningNumbers());
            final LottoNumber bonusNumber = LottoNumber.from(inputView.inputBonusNumber());
            return new WinningNumbers(lastWinningNumbers, bonusNumber);
        } catch (final Exception e) {
            inputView.printErrorMessage(e.getMessage());
            return generateWinningNumbers();
        }
    }

    private void printResult(WinningNumbers winningNumbers) {
        final WinningResult winningResult = lottoService.getMatchResult(winningNumbers);
        final List<LottoMatchKindDto> winningLogs = convertWinningResultsToDtos(winningResult.getWinningNumberByKind());
        outputView.printCountOfWinningByMatchKind(winningLogs);
        outputView.printProfitRate(winningResult.getProfitRate());
    }

    private List<LottoMatchKindDto> convertWinningResultsToDtos(final Map<LottoMatchKind, Integer> results) {
        return results.keySet().stream()
                .map(lottoMatchKind -> new LottoMatchKindDto(lottoMatchKind, results.get(lottoMatchKind)))
                .collect(Collectors.toUnmodifiableList());
    }
}
