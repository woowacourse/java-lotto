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
        lottoGame.calculateStatistics(winnerTicket, generateBonusBall(winnerTicket));
        LottoGameResult lottoGameResult = lottoGame.createResult(money);
        OutputView.noticeStatistics(lottoGameResult.getStatistics(), lottoGameResult.getProfit());
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

    private Money generateMoney() {
        try {
            String inputMoney = InputView.inputMoney();
            return new Money(inputMoney);
        } catch (IllegalArgumentException e) {
            OutputView.printError(e);
            return generateMoney();
        }
    }

    private WinnerTicket generateWinnerTicket() {
        try {
            String inputWinnerTicket = InputView.inputWinnerTicket();
            return new WinnerTicket(inputWinnerTicket);
        } catch (IllegalArgumentException e) {
            OutputView.printError(e);
            return generateWinnerTicket();
        }
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
            String inputNumbers = InputView.inputTicket();
            return () -> LottoTicketMaker.makeLottoNumbers(inputNumbers);
        } catch (IllegalArgumentException e) {
            OutputView.printError(e);
            return generateManualNumbers();
        }
    }

    private ManualLottoCount generateManualLottoCount(LottoCount lottoCount) {
        try {
            String inputManualLottoCount = InputView.inputManualLottoCount();
            return new ManualLottoCount(inputManualLottoCount, lottoCount);
        } catch (IllegalArgumentException e) {
            OutputView.printError(e);
            return generateManualLottoCount(lottoCount);
        }
    }

    private BonusBall generateBonusBall(WinnerTicket winnerTicket) {
        try {
            String inputBonusBall = InputView.inputBonusBall();
            return new BonusBall(inputBonusBall, winnerTicket);
        } catch (IllegalArgumentException e) {
            OutputView.printError(e);
            return generateBonusBall(winnerTicket);
        }
    }
}
