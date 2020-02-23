package domain;

import view.InputView;
import view.OutputView;

import java.util.List;

public class LottoApplication {
    public static void run() {
        Money money = inputMoney();
        List<LottoTicket> originalLottoTickets = LottoNumbersGenerator.generateLottoTickets(money.calculateLottoTicket());
        OutputView.printBuyTicketCount(money.calculateLottoTicket());
        OutputView.printLottoTickets(originalLottoTickets);
        LottoResults lottoResults = new LottoTickets(originalLottoTickets).match(inputWinningLottoTicket());
        OutputView.printWinningStatistics(lottoResults.getCountMap(), money.getMoney());
    }

    public static Money inputMoney() {
        return new Money(InputView.inputBuyMoney());
    }

    public static WinningLottoTicket inputWinningLottoTicket() {
        return new WinningLottoTicket(InputView.inputWinningNumber(), InputView.inputBonusNumber());
    }
}
