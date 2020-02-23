package lotto;

import lotto.domain.*;
import lotto.utils.LottoFactory;
import lotto.utils.NumberGenerator;
import lotto.utils.UserInputNumberGenerator;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;

public class Application {
    public static void main(String[] args) {
        Payment payment = generatePayment();
        OutputView.printLottoCount(payment);

        List<Lotto> lottoList = new Lottos(LottoFactory.createLottoList(payment)).getLottos();
        OutputView.printLottoList(lottoList);

        WinningLotto winningLotto = generateWinningLotto();
        OutputView.printResults(new Results(lottoList, winningLotto));
    }

    private static Payment generatePayment() {
        try {
            return new Payment(InputView.getPayment());
        } catch (IllegalArgumentException exception) {
            OutputView.printErrorMessage(exception.getMessage());
            return generatePayment();
        }
    }

    private static WinningLotto generateWinningLotto() {
        NumberGenerator numberGenerator = new UserInputNumberGenerator();
        try {
            return new WinningLotto(numberGenerator.generateNumbers(InputView.getWinningLottoNumber()), new LottoNumber(InputView.getBonusNumber()));
        } catch (IllegalArgumentException exception) {
            OutputView.printErrorMessage(exception.getMessage());
            return generateWinningLotto();
        }
    }
}
