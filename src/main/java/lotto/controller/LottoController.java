package lotto.controller;

import lotto.domain.Payment;
import lotto.domain.lotto.*;
import lotto.domain.reword.Reword;
import lotto.domain.reword.Rewords;
import lotto.exception.IllegalTypeException;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoController {

    public void run() {
        LottoMachine lottoMachine = new LottoMachine();

        Payment payment = createPayment();
        LottoCount lottoCount = createLottoCount(payment);

        OutputView.printInputLottoNumbers();
        Lottos lottos = new Lottos(lottoCount.auto(), createManualLotto(lottoMachine, lottoCount.manual()));

        showLottos(lottoCount, lottos);
        showResult(lottos, createWinningLotto(lottoMachine), payment);
    }

    private Payment createPayment() {
        try {
            return new Payment(Integer.parseInt(InputView.inputMoney()));
        } catch (NumberFormatException e) {
            throw new IllegalTypeException();
        }
    }

    private LottoCount createLottoCount(Payment payment) {
        try {
            return new LottoCount(payment, Integer.parseInt(InputView.inputManualLottoCount()));
        } catch (NumberFormatException e) {
            throw new IllegalTypeException();
        }
    }

    public List<Lotto> createManualLotto(LottoMachine lottoMachine, final int count) {
        final List<Lotto> lottoTickets = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            lottoMachine.createAndPutLotto(lottoTickets, InputView.inputLottoNumbers());
        }

        return Collections.unmodifiableList(lottoTickets);
    }

    public WinningLotto createWinningLotto(LottoMachine lottoMachine) {
        List<Integer> lottoNumbers = lottoMachine.createLottoNumbers(InputView.inputWinningLottoNumbers());
        return lottoMachine.createWinningLotto(lottoNumbers, InputView.inputBonusNumber());
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
