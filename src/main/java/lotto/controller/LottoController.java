package lotto.controller;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import lotto.domain.Lotto;
import lotto.domain.LottoTickets;
import lotto.domain.Payment;
import lotto.domain.Reword;
import lotto.domain.Rewords;
import lotto.domain.WinningLotto;
import lotto.utils.ParseUtils;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    private static final String REGEX = ", ";

    public LottoController() {}

    public void run() {
        final Payment payment = new Payment(ParseUtils.parseInt(InputView.getInputMoney()));
        final LottoTickets lottoTickets = new LottoTickets(payment.count());
        showLottoTickets(payment, lottoTickets);
        final WinningLotto winningLotto = createWinningLotto();
        callResultMessage(payment, lottoTickets, winningLotto);
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

    private void showLottoTickets(Payment payment, LottoTickets lottoTickets) {
        OutputView.printBuyLottoCountMessage(payment.count());
        for (Lotto lotto : lottoTickets.getLottoTickets()) {
            OutputView.printLottoMessage(lotto.getLottoNumbers());
        }
        OutputView.printNewLineMessage();
    }

    private void callResultMessage(Payment payment, LottoTickets lottoTickets, WinningLotto winningLotto) {
        OutputView.printResultMessage();
        Rewords rewords = lottoTickets.getResult(winningLotto);
        Arrays.stream(Reword.values())
            .sorted(Comparator.comparing(Reword::getWinningMoney))
            .filter(reword -> !reword.equals(reword.NONE))
            .forEach(reword -> callMatchMessage(reword, rewords.getRankCount(reword)));
        OutputView.printProfitMessage(rewords.profit(payment.getPayment()));
    }

    private void callMatchMessage(Reword reword, int count) {
        if (reword != Reword.SECOND) {
            OutputView.printMatchMessage(reword, count);
            return;
        }
        OutputView.printMatchBonusMessage(reword, count);
    }
}
