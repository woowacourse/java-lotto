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

    public static void main(String[] args) {
        final LottoController lottoController = initializeLottoController();

        outputView.printPurchaseCount(lottoController.getManualPurchaseCount(), lottoController.getAutoPurchaseCount());
        outputView.printLottoNumbersGroup(lottoController.getLottos());

        inputWinningInfoAndPrintResult(lottoController);
    }

    private static LottoController initializeLottoController() {
        try {
            final int totalPurchaseAmount = convertStringToInt(inputView.inputPurchaseAmount());
            final int manualPurchaseAmount = convertStringToInt(inputView.inputManualPurchaseAmount());
            final List<String> maunalLottoInfos = inputView.inputManualLottoNumbers(manualPurchaseAmount);
            return new LottoController(totalPurchaseAmount, maunalLottoInfos);
        } catch (final Exception e) {
            inputView.printErrorMessage(e.getMessage());
            return initializeLottoController();
        }
    }

    private static void inputWinningInfoAndPrintResult(final LottoController lottoController) {
        try {
            final List<Integer> winningLotto = getWinningLotto();
            final int bonusBall = inputBonusBall();
            outputView.printCountOfWinningByMatchKind(lottoController.getWinningResult(winningLotto, bonusBall));
            outputView.printProfitRate(lottoController.getProfitRate(winningLotto, bonusBall));
        } catch (final Exception e) {
            inputView.printErrorMessage(e.getMessage());
            inputWinningInfoAndPrintResult(lottoController);
        }
    }

    private static List<Integer> getWinningLotto() {
        final List<String> winningLotto = inputView.inputLastWeekWinningNumbers();
        return winningLotto.stream()
                .map(NumberConverter::convertStringToInt)
                .collect(Collectors.toUnmodifiableList());
    }

    private static int inputBonusBall() {
        return convertStringToInt(inputView.inputBonusNumber());
    }
}
