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
        LottoCount lottoCount = new LottoCount(money.divideMoney(LOTTO_PRICE));
        LottoGame lottoGame = new LottoGame(generateTickets(lottoCount));
        WinnerTicket winnerTicket = generateWinnerTicket();
        OutputView.noticeStatistics(
                lottoGame.calculateStatistics(winnerTicket, generateBonusBall(winnerTicket)),
                lottoGame.calculateResult(money));
    }

    private LottoTickets generateTickets(LottoCount lottoCount) {
        ManualLottoCount manualLottoCount = generateManualLottoCount(lottoCount);
        LottoCount autoLottoCount = manualLottoCount.makeAutoCount(lottoCount);

        LottoTickets lottoTickets = generateManualLottoTickets(manualLottoCount);
        lottoTickets.addTickets(autoLottoCount, new RandomNumbersGenerator());

        OutputView.noticeLottoCount(manualLottoCount, autoLottoCount);
        OutputView.showTickets(lottoTickets);
        return lottoTickets;
    }

    private LottoTickets generateManualLottoTickets(ManualLottoCount manualLottoCount) {
        try {
            return new LottoTickets(manualLottoCount, generateManualNumbers());
        } catch (IllegalArgumentException e) {
            OutputView.printError(e);
            return generateManualLottoTickets(manualLottoCount);
        }
    }

    private NumbersGenerator generateManualNumbers() {
        try {
            OutputView.enterManualLottoTickets();
            return () -> LottoTicketMaker.makeLottoNumbers(InputView.inputTicket());
        } catch (IllegalArgumentException e) {
            OutputView.printError(e);
            return generateManualNumbers();
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
