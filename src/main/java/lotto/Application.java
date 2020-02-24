package lotto;

import lotto.domain.*;
import lotto.utils.LottoFactory;
import lotto.utils.NumberGenerator;
import lotto.utils.UserInputNumberGenerator;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Application {
    public static void main(String[] args) {
        final Payment payment = generatePayment();
        OutputView.printLottoCount(payment);

        final LottoTickets lottoTickets = LottoFactory.createLottoList(payment);
        OutputView.printLottoList(lottoTickets);

        final WinningLottoTicket winningLotto = generateWinningLotto();
        OutputView.printResults(new Results(lottoTickets, winningLotto));
    }

    private static Payment generatePayment() {
        try {
            return new Payment(InputView.getPayment());
        } catch (IllegalArgumentException exception) {
            String errorMessage = exception.getMessage();
            OutputView.printErrorMessage(errorMessage);
            return generatePayment();
        }
    }

    private static WinningLottoTicket generateWinningLotto() {
        NumberGenerator numberGenerator = new UserInputNumberGenerator();
        String WinningLottoInput = InputView.getWinningLottoNumber();
        String bonusNumberInput = InputView.getBonusNumber();

        try {
            return new WinningLottoTicket(numberGenerator.generateNumbers(WinningLottoInput), new LottoNumber(bonusNumberInput));
        } catch (IllegalArgumentException exception) {
            String errorMessage = exception.getMessage();
            
            OutputView.printErrorMessage(errorMessage);
            return generateWinningLotto();
        }
    }
}
