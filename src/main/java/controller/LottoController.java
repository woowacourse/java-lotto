package controller;

import domain.*;
import view.InputConvertor;
import view.OutputView;

public class LottoController {

    public void run() {
        Payment payment = InputConvertor.createPayment();
        LottoOrder lottoOrder = InputConvertor.createManualTicketCount(payment);
        LottoGame lottoGame = new LottoGame(new LottoMachine(), lottoOrder);

        Lottos lottos = createLottos(lottoGame);
        WinningLotto winningLotto = InputConvertor.createWinningLotto();

        LottoResult lottoResult = lottoGame.createLottoResult(lottos, winningLotto);
        OutputView.printRankCounts(lottoResult.countRank());
        OutputView.printProfitRate(lottoResult.calculateProfitRate(payment));
    }

    private Lottos createLottos(LottoGame lottoGame) {
        Lottos lottos = lottoGame.createLottos();
        OutputView.printLottos(lottos, lottoGame.getManualTicketCount(), lottoGame.getAutoTicketCount());
        return lottos;
    }
}
