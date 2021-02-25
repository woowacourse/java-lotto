package lotto.lottogame;

import lotto.lottoticket.BonusBall;
import lotto.lottoticket.LottoTicketMaker;
import lotto.lottoticket.LottoTickets;
import lotto.lottoticket.WinnerTicket;
import lotto.lottoticket.ticketnumber.NumbersGenerator;
import lotto.lottoticket.ticketnumber.RandomNumbersGenerator;
import lotto.money.Money;
import lotto.view.InputView;
import lotto.view.OutputView;

import static lotto.lottogame.LottoCount.LOTTO_PRICE;

public class LottoController {
    public void run() {
        Money money = generateMoney();
        LottoTickets lottoTickets = generateTickets(money);
        LottoGame lottoGame = new LottoGame(lottoTickets);
        WinnerTicket winnerTicket = generateWinnerTicket();
        OutputView.noticeStatistics(
                lottoGame.calculateStatistics(winnerTicket, generateBonusBall(winnerTicket)),
                lottoGame.calculateResult(money));
    }

    private LottoTickets generateTickets(Money money) {
        LottoCount lottoCount = new LottoCount(money.divideMoney(LOTTO_PRICE));
        ManualLottoCount manualLottoCount = generateManualLottoCount(lottoCount);
        LottoCount autoLottoCount = manualLottoCount.makeAutoCount(lottoCount);
        LottoTickets lottoTickets = new LottoTickets(manualLottoCount, generateManualLottoTicket());
        lottoTickets.addTickets(autoLottoCount, new RandomNumbersGenerator());
        OutputView.noticeLottoCount(manualLottoCount, autoLottoCount);
        OutputView.showTickets(lottoTickets);
        return lottoTickets;
    }

    private NumbersGenerator generateManualLottoTicket() {
        try {
            OutputView.enterManualLottoTickets();
            return () -> LottoTicketMaker.makeLottoNumbers(InputView.inputTicket());
        } catch (IllegalArgumentException e) {
            OutputView.printError(e);
            return generateManualLottoTicket();
        }
    }

    private ManualLottoCount generateManualLottoCount(LottoCount lottoCount) {
        try {
            OutputView.noticeManualLottoCount();
            return InputView.inputManualLottoCount(lottoCount);
        } catch (IllegalArgumentException e) {
            OutputView.printError(e);
            return generateManualLottoCount(lottoCount);
        }
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
