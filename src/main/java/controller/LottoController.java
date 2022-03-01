package controller;

import domain.LottoGame;
import domain.LottoNumber;
import domain.LottoTicket;
import domain.UserBalance;
import domain.WinningLotto;
import domain.LottoTickets;

import java.util.List;

import view.InputView;
import view.OutputView;

public class LottoController {

    public void run() {
        UserBalance userBalance = new UserBalance(InputView.requestUserBalance(), InputView.requestManualLottoCount());
        LottoTickets lottoTickets = initLottoTickets(userBalance);
        WinningLotto winningLotto = initWinningLotto();
        LottoGame lottoGame = new LottoGame(lottoTickets, winningLotto);

        OutputView.printLottoResults(lottoGame.getResultStatistics());
        OutputView.printProfitRatio(lottoGame.calculateProfitRatio(userBalance.getUserBalance()));
    }

    private LottoTickets initLottoTickets(UserBalance userBalance) {
        List<LottoTicket> manualLottoTickets = InputView.requestManualLottoTickets(userBalance.getManualLottoCount());

        LottoTickets lottoTickets = new LottoTickets();
        lottoTickets.purchaseManualBy(manualLottoTickets);
        lottoTickets.purchaseAutoBy(userBalance.getAutoLottoCount());

        OutputView.printPurchaseInfo(userBalance.getManualLottoCount(), userBalance.getAutoLottoCount(),
                lottoTickets.getLottoTickets());

        return lottoTickets;
    }

    private WinningLotto initWinningLotto() {
        LottoTicket winningNumbers = InputView.requestWinningNumbers();
        LottoNumber bonusNumber = InputView.requestBonusNumber();

        return new WinningLotto(winningNumbers, bonusNumber);
    }
}
