package lotto;

import lotto.domain.*;
import lotto.factory.LottoFactory;
import lotto.generator.NumberGenerator;
import lotto.generator.UserInputNumberGenerator;
import lotto.utils.NumberUtils;
import lotto.validator.Validator;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;

public class Application {
    public static void main(String[] args) {
        final Payment payment = generatePayment();
        OutputView.printLottoCount(payment);

        final ManualPurchase manualPurchase = generateManualPurchase(payment);

        final LottoTickets lottoTickets = LottoFactory.createLottoList(manualPurchase, payment);
        OutputView.printLottoList(lottoTickets);

        final WinningLottoTicket winningLotto = generateWinningLotto();
        OutputView.printResults(new Results(lottoTickets, winningLotto));
    }

    private static ManualPurchase generateManualPurchase(Payment payment) {
        try {
            int parsedManualCount = NumberUtils.parseNumber(InputView.getManualCount());

            validateManualCount(parsedManualCount, payment);
            List<String> manualLottoInput = InputView.getManualLottoNumbers(parsedManualCount);
            return new ManualPurchase(parsedManualCount, manualLottoInput);
        } catch (IllegalArgumentException exception) {
            OutputView.printErrorMessage(exception);
            return generateManualPurchase(payment);
        }
    }

    private static void validateManualCount(int parsedManualCount, Payment payment) {
        Validator.validateLessThanLottoCount(parsedManualCount, payment);
        Validator.validatePositiveNumber(parsedManualCount);
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
