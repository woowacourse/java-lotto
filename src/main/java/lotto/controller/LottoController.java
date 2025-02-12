package lotto.controller;

import lotto.domain.Lotto;
import lotto.domain.Rank;
import lotto.service.LottoService;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class LottoController {

    private final InputView inputView;
    private final OutputView outputView;
    private final LottoService lottoService;

    public LottoController(InputView inputView, OutputView outputView, LottoService lottoService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.lottoService = lottoService;
    }

    public void run() {
        int payment = inputView.readPayment();
        List<Lotto> lottos = lottoService.buyLottos(payment);
        outputView.printTicket(lottos);

        List<Integer> winningNumbers = inputView.readWinningNumbers();
        int bonusNumber = inputView.readBonusNumber();

        Map<Rank, Integer> rankCount = lottoService.getResult(winningNumbers, bonusNumber);
        double rateOfReturn = lottoService.getRateOfReturn(rankCount);
        outputView.printResult(rankCount, rateOfReturn);
    }
}
