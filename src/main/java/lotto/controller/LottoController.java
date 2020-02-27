package lotto.controller;

import lotto.domain.result.LottoResultBundle;
import lotto.domain.result.MatchResultBundle;
import lotto.domain.result.win.WinningLotto;
import lotto.domain.ticket.BettingInfo;
import lotto.domain.ticket.BettingMoney;
import lotto.domain.ticket.LottoTicketBundle;
import lotto.domain.ticket.manual.ManualNumberBundle;
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
        BettingInfo bettingInfo = new BettingInfo(bettingMoney, inputView.inputManualTicketAmount());
        ManualNumberBundle manualNumberBundle = new ManualNumberBundle(inputView.inputManualNumbers(bettingInfo.getManualAmount()));

        OutputView.printBuyTicketCount(bettingInfo);

        LottoTicketBundle lottoTicketBundle = lottoService.getLottoTicketBundle(bettingInfo, manualNumberBundle);

        OutputView.printBuyTickets(lottoTicketBundle);

        Set<Integer> winningNumber = inputView.inputWinningNumber();
        int bonusNumber = inputView.inputBonusNumber();
        WinningLotto winningLotto = new WinningLotto(winningNumber, bonusNumber);

        MatchResultBundle matchResultBundle = lottoTicketBundle.getMatchResultBundle(winningLotto);
        LottoResultBundle lottoResultBundle = matchResultBundle.getPrizeBundle();

        OutputView.printLottoResult(lottoResultBundle);
    }

}
