package lotto.lottogame;

import lotto.lottoticket.BonusBall;
import lotto.lottoticket.WinnerTicket;
import lotto.money.Money;
import lotto.ranking.Statistics;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {
    public void run() {
        Money money = generateMoney();
        LottoGame lottoGame = new LottoGame(generateCount(money));
        OutputView.showTickets(lottoGame.getLottoTickets());

        WinnerTicket winnerTicket = generateWinnerTicket();
        Statistics statistics = lottoGame.createStatistics(winnerTicket, generateBonusBall(winnerTicket));
        OutputView.noticeStatistics(statistics);
        OutputView.showProfit(lottoGame.createResult(statistics, money));
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

    private LottoCount generateCount(Money money) {
        try {
            LottoCount lottoCount = LottoCount.valueOf(money);
            OutputView.noticeLottoCount(lottoCount);
            return lottoCount;
        } catch (IllegalArgumentException e) {
            OutputView.printError(e);
            return generateCount(money);
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
