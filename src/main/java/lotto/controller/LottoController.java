package lotto.controller;

import lotto.domain.Payment;
import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.LottoCount;
import lotto.domain.lotto.Lottos;
import lotto.domain.lotto.WinningLotto;
import lotto.domain.reword.Reword;
import lotto.domain.reword.Rewords;
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
            LottoCount lottoCount = new LottoCount(payment, Integer.parseInt(InputView.inputManualLottoCount()));

            Lottos lottos = new Lottos(lottoCount.auto(), createManualLotto(lottoCount.manual()));
            showLottos(lottoCount, lottos);

            WinningLotto winningLotto = createWinningLotto();
            showResult(lottos, winningLotto, payment);
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

    private void showLottos(LottoCount ticketCount, Lottos lottos) {
        OutputView.printBuyLottoCountMessage(ticketCount.manual(), ticketCount.auto());

        for (Lotto lotto : lottos.getLottos()) {
            OutputView.printLottoMessage(lotto.getLottoNumbers());
        }

        OutputView.printNewLineMessage();
    }

    private void showResult(final Lottos lottos, final WinningLotto winningLotto, final Payment payment) {
        Rewords rewords = lottos.createRewords(winningLotto);

        OutputView.printResult();
        for (Reword reword : Reword.values()) {
            OutputView.printReword(reword.getHitCount(), reword.getWinningMoney(), rewords.countOfReword(reword));
        }
        OutputView.printProfit(rewords, payment.getPayment());
    }
}
