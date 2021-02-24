package lotto.lottogame;

import lotto.lottoticket.BonusBall;
import lotto.lottoticket.LottoTickets;
import lotto.lottoticket.WinnerTicket;
import lotto.lottoticket.ticketnumber.RandomNumbersGenerator;
import lotto.money.Money;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {
    public void run() {
        Money money = generateMoney();
        LottoCount lottoCount = new LottoCount(money);
        LottoTickets lottoTickets = new LottoTickets(lottoCount, new RandomNumbersGenerator());
        LottoGame lottoGame = new LottoGame(lottoTickets);
        OutputView.noticeLottoCount(lottoCount);
        OutputView.showTickets(lottoTickets);
        WinnerTicket winnerTicket = generateWinnerTicket();
        OutputView.noticeStatistics(
                lottoGame.calculateStatistics(winnerTicket, generateBonusBall(winnerTicket)),
                lottoGame.calculateResult(money));
    }

    private Money generateMoney() {
        try {
            OutputView.enterPurchaseMoney();
            return InputView.inputMoney();
        } catch (IllegalArgumentException e) {
            OutputView.printError(e);
            return generateMoney();
        }
    }

    private WinnerTicket generateWinnerTicket() {
        try {
            OutputView.enterWinnerTicket();
            return InputView.inputWinnerTicket();
        } catch (IllegalArgumentException e) {
            OutputView.printError(e);
            return generateWinnerTicket();
        }
    }

    private BonusBall generateBonusBall(WinnerTicket winnerTicket) {
        try {
            OutputView.enterBonusBall();
            return InputView.inputBonusBall(winnerTicket);
        } catch (IllegalArgumentException e) {
            OutputView.printError(e);
            return generateBonusBall(winnerTicket);
        }
    }
}
