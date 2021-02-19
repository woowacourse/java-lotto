package lotto.controller;

import lotto.domain.*;
import lotto.exception.IllegalTypeException;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LottoController {

    private static final String REGEX = ",";
    private static final String REGEX_WITH_SPACE = ", ";

    public void run() {
        try {
            Payment payment = new Payment(Integer.parseInt(InputView.inputMoney()));
            LottoTickets lottoTickets = new LottoTickets(payment.count());
            showLottoTickets(payment, lottoTickets);

            WinningLotto winningLotto = createWinningLotto();
            showResult(lottoTickets, winningLotto, payment);
        }
        catch (NumberFormatException e) {
            throw new IllegalTypeException();
        }
    }

    private WinningLotto createWinningLotto() {
        String values = InputView.lottoNumber().replaceAll(REGEX_WITH_SPACE, REGEX);
        List<Integer> numbers = Arrays.stream(values.split(REGEX))
            .mapToInt(Integer::parseInt)
            .boxed()
            .collect(Collectors.toList());
        int bonusNumber = Integer.parseInt(InputView.bonusNumber());

        return new WinningLotto(numbers, bonusNumber);
    }

    private void showLottoTickets(Payment payment, LottoTickets lottoTickets) {
        OutputView.printBuyLottoCountMessage(payment.count());

        for (Lotto lotto : lottoTickets.getLottoTickets()) {
            OutputView.printLottoMessage(lotto.getLottoNumbers());
        }

        OutputView.printNewLineMessage();
    }

    private void showResult(final LottoTickets lottoTickets, final WinningLotto winningLotto, final Payment payment) {
        Rewords rewords = lottoTickets.createRewords(winningLotto);

        OutputView.printResult();
        for (Reword reword : Reword.values()) {
            OutputView.printReword(reword.getHitCount(), reword.getWinningMoney(), rewords.countOfReword(reword));
        }
        OutputView.printProfit(rewords, payment.getPayment());
    }
}
