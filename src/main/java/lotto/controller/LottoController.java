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
        try {
            LottoMachine lottoMachine = new LottoMachine();

            Payment payment = new Payment(Integer.parseInt(InputView.inputMoney()));
            LottoCount lottoCount = new LottoCount(payment, Integer.parseInt(InputView.inputManualLottoCount()));

            OutputView.printInputLottoNumbers();
            Lottos lottos = new Lottos(lottoCount.auto(), createManualLotto(lottoMachine, lottoCount.manual()));
            showLottos(lottoCount, lottos);

            WinningLotto winningLotto = createWinningLotto(lottoMachine);
            showResult(lottos, winningLotto, payment);
        }
        catch (NumberFormatException e) {
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
