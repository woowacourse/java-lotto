package lotto.controller;

import lotto.domain.LottoMachine;
import lotto.domain.generator.LottoRandomGenerator;
import lotto.domain.lottonumber.Lotto;
import lotto.domain.lottonumber.Lottos;
import lotto.domain.lottonumber.WinningNumbers;
import lotto.domain.lottonumber.vo.LottoNumber;
import lotto.domain.matchkind.LottoMatchKind;
import lotto.domain.purchaseamount.ManualPurchaseCount;
import lotto.domain.purchaseamount.TotalPurchaseAmount;
import lotto.domain.winningresult.WinningResult;
import lotto.dto.InputLottoDto;
import lotto.dto.LottoMatchKindDto;
import lotto.dto.LottoNumbersDto;
import lotto.view.input.InputView;
import lotto.view.output.OutputView;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LottoController {
    private final LottoMachine lottoMachine;
    private final InputView inputView;
    private final OutputView outputView;

    public LottoController(final InputView inputView, final OutputView outputView, final int lottoPrice) {
        this.inputView = inputView;
        this.outputView = outputView;
        lottoMachine = initializeLottoService(lottoPrice);
    }

    private LottoMachine initializeLottoService(final int lottoPrice) {
        final TotalPurchaseAmount totalPurchaseAmount = inputTotalPurchaseAmount(lottoPrice);
        final int manualPurchaseAmount = inputManualPurchaseAmount(totalPurchaseAmount);
        final List<Lotto> manualLottos = inputManualLottos(manualPurchaseAmount);
        return new LottoMachine(new LottoRandomGenerator(), totalPurchaseAmount, manualLottos);
    }

    private TotalPurchaseAmount inputTotalPurchaseAmount(final int lottoPrice) {
        try {
            final String purchaseAmountInput = inputView.inputPurchaseAmount();
            return new TotalPurchaseAmount.TotalPurchaseAmountBuilder()
                    .setTotalAmount(purchaseAmountInput)
                    .setLottoPrice(lottoPrice)
                    .build();
        } catch (final Exception e) {
            inputView.printErrorMessage(e.getMessage());
            return inputTotalPurchaseAmount(lottoPrice);
        }
    }

    private int inputManualPurchaseAmount(final TotalPurchaseAmount totalPurchaseAmount) {
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
        outputView.printPurchaseCount(lottoMachine.getCountOfLottoNumbers());
        printLottoNumbersGroup();
        final WinningNumbers winningNumbers = generateWinningNumbers();
        printResult(winningNumbers);
    }

    private void printLottoNumbersGroup() {
        final List<LottoNumbersDto> numbersGroup =
                convertLottoNumbersGroupToDtos(lottoMachine.getLottos());
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
        final WinningResult winningResult = lottoMachine.getMatchResult(winningNumbers);
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
