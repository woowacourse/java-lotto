package lotto.controller;

import lotto.domain.*;
import lotto.exception.IllegalTypeException;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class LottoController {

    private static final String REGEX = ",";
    private static final String REGEX_WITH_SPACE = ", ";

    public void run() {
        try {
            Payment payment = new Payment(Integer.parseInt(InputView.inputMoney()));
            TicketCount ticketCount = new TicketCount(payment, Integer.parseInt(InputView.inputManualLottoCount()));

            LottoTickets lottoTickets = new LottoTickets(ticketCount.auto(), createManualLotto(ticketCount.manual()));
            showLottoTickets(ticketCount, lottoTickets);

            WinningLotto winningLotto = createWinningLotto();
            showResult(lottoTickets, winningLotto, payment);
        }
        catch (NumberFormatException e) {
            throw new IllegalTypeException();
        }
    }

    private List<Lotto> createManualLotto(final int count) {
        final List<Lotto> lottoTickets = new ArrayList<>();

        OutputView.printInputLottoNumbers();
        for (int i = 0; i < count; i++) {
            lottoTickets.add(createLotto());
        }

        return Collections.unmodifiableList(lottoTickets);
    }

    private Lotto createLotto() {
        String values = InputView.inputLottoNumbers().replaceAll(REGEX_WITH_SPACE, REGEX);
        List<Integer> numbers = Arrays.stream(values.split(REGEX))
                .mapToInt(Integer::parseInt)
                .boxed()
                .collect(Collectors.toList());

        return new Lotto(numbers);
    }

    private WinningLotto createWinningLotto() {
        String values = InputView.inputWinningLottoNumbers().replaceAll(REGEX_WITH_SPACE, REGEX);
        List<Integer> numbers = Arrays.stream(values.split(REGEX))
            .mapToInt(Integer::parseInt)
            .boxed()
            .collect(Collectors.toList());
        int bonusNumber = Integer.parseInt(InputView.inputBonusNumber());

        return new WinningLotto(numbers, bonusNumber);
    }

    private void showLottoTickets(TicketCount ticketCount, LottoTickets lottoTickets) {
        OutputView.printBuyLottoCountMessage(ticketCount.manual(), ticketCount.auto());

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
