package lotto.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lotto.domain.Lotto;
import lotto.domain.LottoTickets;
import lotto.domain.Payment;
import lotto.domain.WinningLotto;
import lotto.utils.ParseUtils;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    private static final String REGEX = ", ";

    private LottoTickets lottoTickets;
    private WinningLotto winningLotto;
    private Payment payment;

    public LottoController() {}

    public void run() {
        payment = new Payment(ParseUtils.parseInt(InputView.getInputMoney()));
        lottoTickets = new LottoTickets(payment.count());
        showLottoTickets();
        winningLotto = createWinningLotto();
        OutputView.printResultMessage(lottoTickets.getResult(winningLotto), payment.getPayment());
    }

    private WinningLotto createWinningLotto() {
        String values = InputView.getLottoNumbers();
        List<Integer> numbers = Arrays.stream(values.split(REGEX))
            .mapToInt(Integer::parseInt)
            .boxed()
            .collect(Collectors.toList());
        int bonusNumber = ParseUtils.parseInt(InputView.getBonusBallNumber());
        return new WinningLotto(numbers, bonusNumber);
    }

    private void showLottoTickets() {
        OutputView.printBuyLottoCountMessage(payment.count());
        for (Lotto lotto : lottoTickets.getLottoTickets()) {
            OutputView.printLottoMessage(lotto.getLottoNumbers());
        }
        OutputView.printNewLineMessage();
    }
}
