package lotto.controller;

import lotto.domain.Lotto;
import lotto.domain.Rank;
import lotto.dto.WinningBallsDto;
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
        buyLottoTicket();
        setWinningBalls();
        getResult();
    }

    private void buyLottoTicket() {
        int payment = inputView.readPayment();
        List<Lotto> lottos = lottoService.buyLottos(payment);
        outputView.printTicket(lottos);
    }

    private void setWinningBalls() {
        WinningBallsDto winningBallsDto = inputView.readWinningBalls();
        lottoService.setWinningBalls(winningBallsDto);
    }

    private void getResult() {
        Map<Rank, Integer> rankCount = lottoService.getResult();
        double rateOfReturn = lottoService.getRateOfReturn(rankCount);
        outputView.printResult(rankCount, rateOfReturn);
    }
}
