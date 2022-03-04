package lotto;

import lotto.controller.LottoController;
import lotto.util.converter.NumberConverter;
import lotto.view.input.ConsoleInputView;
import lotto.view.input.InputView;
import lotto.view.output.ConsoleOutputView;
import lotto.view.output.OutputView;

import java.util.List;
import java.util.stream.Collectors;

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
        final List<Integer> winningLotto = getWinningLotto();
        final int bonusBall = inputBonusBall();

        outputView.printCountOfWinningByMatchKind(lottoController.getWinningResult(winningLotto, bonusBall));
        outputView.printProfitRate(lottoController.getProfitRate(winningLotto, bonusBall));

    }

    private static void inputPurchaseAmount() {
        try {
            final int purchaseAmount = Integer.parseInt(inputView.inputPurchaseAmount());
            lottoController.setPurchaseAmount(purchaseAmount);
        } catch (final Exception e) {
            inputView.printErrorMessage(e.getMessage());
            inputPurchaseAmount();
        }
    }

    private static int inputManualPurchaseCount() {
        try {
            final String manualPurchaseCount = inputView.inputManualPurchaseAmount();
            return Integer.parseInt(manualPurchaseCount);
        } catch (final Exception e) {
            inputView.printErrorMessage(e.getMessage());
            return inputManualPurchaseCount();
        }
    }

    private static void inputManualLottoNumbers(final int manualPurchaseCount) {
        try {
            final List<String> manualLottos = inputView.inputManualLottoNumbers(manualPurchaseCount);
            lottoController.setManualLottoNumbers(manualLottos);
        } catch (final IllegalArgumentException e) {
            inputView.printErrorMessage(e.getMessage());
            inputManualLottoNumbers(manualPurchaseCount);
        }
    }

    private static List<Integer> getWinningLotto() {
        final List<String> winningLotto = inputView.inputLastWeekWinningNumbers();
        try {
            return winningLotto.stream()
                    .map(NumberConverter::convertStringToInt)
                    .collect(Collectors.toUnmodifiableList());
        } catch (Exception e) {
            inputView.printErrorMessage(e.getMessage());
            return getWinningLotto();
        }
    }

    private static int inputBonusBall() {
        try {
            return Integer.parseInt(inputView.inputBonusNumber());
        } catch (final Exception e) {
            inputView.printErrorMessage(e.getMessage());
            return inputBonusBall();
        }
    }
}
