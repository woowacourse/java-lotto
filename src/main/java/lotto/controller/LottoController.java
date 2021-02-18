package lotto.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lotto.domain.Lotto;
import lotto.domain.LottoTickets;
import lotto.domain.Payment;
import lotto.domain.WinningLotto;
import lotto.utils.ParseUtils;
import lotto.view.OutputView;
import lotto.view.Screen;

public class LottoController {

    private static final String REGEX = ", ";

    private final LottoTickets lottoTickets;
    private final WinningLotto winningLotto;
    private final Payment payment;

    public LottoController(final String value) {
        payment = new Payment(ParseUtils.parseInt(value));
        lottoTickets = new LottoTickets(payment.count());
        showLottoTickets();
        winningLotto = createWinningLotto();
    }

    public void run() {
        OutputView.printResultMessage(lottoTickets.getResult(winningLotto), payment.getPayment());
    }

    private WinningLotto createWinningLotto() {
        String values = Screen.getLottoNumbers();
        List<Integer> numbers = Arrays.stream(values.split(REGEX))
            .mapToInt(Integer::parseInt)
            .boxed()
            .collect(Collectors.toList());
        int bonusNumber = ParseUtils.parseInt(Screen.getBonusBallNumber());
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
