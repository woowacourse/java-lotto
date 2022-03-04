package lotto;

import lotto.controller.LottoController;
import lotto.util.converter.NumberConverter;
import lotto.view.input.ConsoleInputView;
import lotto.view.input.InputView;
import lotto.view.output.ConsoleOutputView;
import lotto.view.output.OutputView;

import java.util.List;
import java.util.stream.Collectors;

import static lotto.util.converter.NumberConverter.convertStringToInt;

public class Application {
    private static final InputView inputView = new ConsoleInputView();
    private static final OutputView outputView = new ConsoleOutputView();
    private static final LottoController lottoController = new LottoController();

    public static void main(String[] args) {
        inputPurchaseAmount();
        final int manualPurchaseCount = inputManualPurchaseCount();
        inputManualLottoNumbers(manualPurchaseCount);

        outputView.printPurchaseCount(lottoController.getManualPurchaseCount(), lottoController.getAutoPurchaseCount());
        outputView.printLottoNumbersGroup(lottoController.getLottos());

        inputWinningInfoAndPrintResult();
    }

    private static void inputWinningInfoAndPrintResult() {
        try {
            final List<Integer> winningLotto = getWinningLotto2();
            final int bonusBall = inputBonusBall2();
            outputView.printCountOfWinningByMatchKind(lottoController.getWinningResult(winningLotto, bonusBall));
            outputView.printProfitRate(lottoController.getProfitRate(winningLotto, bonusBall));
        } catch (final Exception e) {
            inputView.printErrorMessage(e.getMessage());
            inputWinningInfoAndPrintResult();
        }
    }

    private static void inputPurchaseAmount() {
        try {
            final int purchaseAmount = convertStringToInt(inputView.inputPurchaseAmount());
            lottoController.setPurchaseAmount(purchaseAmount);
        } catch (final Exception e) {
            inputView.printErrorMessage(e.getMessage());
            inputPurchaseAmount();
        }
    }

    private static int inputManualPurchaseCount() {
        try {
            final String manualPurchaseCount = inputView.inputManualPurchaseAmount();
            return convertStringToInt(manualPurchaseCount);
        } catch (final Exception e) {
            inputView.printErrorMessage(e.getMessage());
            return inputManualPurchaseCount();
        }
    }

    private static void inputManualLottoNumbers(final int manualPurchaseCount) {
        try {
            final List<String> manualLottos = inputView.inputManualLottoNumbers(manualPurchaseCount);
            lottoController.setManualLottoNumbers(manualLottos);
        } catch (final Exception e) {
            inputView.printErrorMessage(e.getMessage());
            inputManualLottoNumbers(manualPurchaseCount);
        }
    }

    private static List<Integer> getWinningLotto2() {
        final List<String> winningLotto = inputView.inputLastWeekWinningNumbers();
        return winningLotto.stream()
                .map(NumberConverter::convertStringToInt)
                .collect(Collectors.toUnmodifiableList());
    }

    private static int inputBonusBall2() {
        return convertStringToInt(inputView.inputBonusNumber());
    }
}
