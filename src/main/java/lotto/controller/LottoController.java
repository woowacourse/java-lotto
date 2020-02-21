package lotto.controller;

import lotto.domain.result.LottoResultBundle;
import lotto.domain.result.MatchResultBundle;
import lotto.domain.result.win.WinningLotto;
import lotto.domain.ticket.BettingMoney;
import lotto.domain.ticket.LottoTicketBundle;
import lotto.service.LottoService;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.Scanner;
import java.util.Set;

public class LottoController {

    private static final InputView inputView = new InputView(new Scanner(System.in));
    private final LottoService lottoService;

    public LottoController(LottoService lottoService) {
        this.lottoService = lottoService;
    }

    public void run() {
        BettingMoney bettingMoney = BettingMoney.valueOf(inputView.inputBettingMoney());
        OutputView.printBuyTicketCount(bettingMoney.getTicketCount());

        LottoTicketBundle lottoTicketBundle = lottoService.getLottoTicketBundle(bettingMoney);
        OutputView.printBuyTickets(lottoTicketBundle);

        LottoResultBundle lottoResultBundle = makeLottoResultBundle(lottoTicketBundle);
        OutputView.printLottoResult(lottoResultBundle);
    }

    private LottoResultBundle makeLottoResultBundle(LottoTicketBundle lottoTicketBundle) {
        Set<Integer> winningNumber = inputView.inputWinningNumber();
        int bonusNumber = inputView.inputBonusNumber();

        WinningLotto winningLotto = new WinningLotto(winningNumber, bonusNumber);
        MatchResultBundle matchResultBundle = lottoTicketBundle.getMatchResultBundle(winningLotto);

        return matchResultBundle.getPrizeBundle();
    }

}
