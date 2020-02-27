package lotto;

import lotto.domain.*;
import lotto.factory.LottoFactory;
import lotto.generator.NumberGenerator;
import lotto.generator.UserInputNumberGenerator;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.Collections;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        final Payment payment = generatePayment();
        OutputView.printLottoCount(payment);

        final ManualPurchase manualPurchase = generateManualPurchase(generateManualPurchaseCount(payment));
        final LottoTickets lottoTickets = LottoFactory.createLottoList(manualPurchase, payment);
        OutputView.printLottoList(lottoTickets);

        final WinningLottoTicket winningLotto = generateWinningLotto();
        OutputView.printResults(new Results(lottoTickets, winningLotto));
    }

    private static ManualPurchaseCount generateManualPurchaseCount(Payment payment) {
        try {
            return new ManualPurchaseCount(InputView.getManualCount(), payment);
        } catch (IllegalArgumentException exception) {
            OutputView.printErrorMessage(exception);
            return generateManualPurchaseCount(payment);
        }
    }

    private static ManualPurchase generateManualPurchase(ManualPurchaseCount manualPurchaseCount) {
        try {
            final List<String> manualLottoInput = getManualInput(manualPurchaseCount);
            return new ManualPurchase(manualPurchaseCount, manualLottoInput);
        } catch (IllegalArgumentException exception) {
            OutputView.printErrorMessage(exception);
            return generateManualPurchase(manualPurchaseCount);
        }
    }

    private static List<String> getManualInput(ManualPurchaseCount manualPurchaseCount) {
        if (manualPurchaseCount.getPurchasedCount() > 0) {
            return InputView.getManualLottoNumbers(manualPurchaseCount);
        }
        return Collections.emptyList();
    }

    private static Payment generatePayment() {
        try {
            return new Payment(InputView.getPayment());
        } catch (IllegalArgumentException exception) {
            OutputView.printErrorMessage(exception);
            return generatePayment();
        }
    }

    private static WinningLottoTicket generateWinningLotto() {
        try {
            NumberGenerator numberGenerator = new UserInputNumberGenerator();
            String winningLottoInput = InputView.getWinningLottoNumber();
            String bonusNumberInput = InputView.getBonusNumber();

            return new WinningLottoTicket(numberGenerator.generateNumbers(winningLottoInput), new LottoNumber(bonusNumberInput));
        } catch (IllegalArgumentException exception) {
            OutputView.printErrorMessage(exception);
            return generateWinningLotto();
        }
    }
}
