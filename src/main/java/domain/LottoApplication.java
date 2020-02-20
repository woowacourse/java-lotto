package domain;

import view.InputView;
import view.OutputView;

import java.util.HashMap;
import java.util.List;

public class LottoApplication {
    public static void run() {
        Money money = new Money(InputView.inputBuyMoney());
        int ticketCount = money.calculateLottoTicket();
        List<LottoTicket> originalLottoTickets = LottoNumbersGenerator.generateLottoTickets(ticketCount);

        OutputView.printBuyTicketCount(ticketCount);
        OutputView.printLottoTickets(originalLottoTickets);

        WinningLottoTicket winningLottoTicket = new WinningLottoTicket(InputView.inputWinningNumber());
        winningLottoTicket.initializeBonusBall(InputView.inputBonusNumber());

        LottoTickets lottoTickets = new LottoTickets(originalLottoTickets);

        LottoResults lottoResults = lottoTickets.match(winningLottoTicket);
        HashMap<String, Integer> map = lottoResults.getCountMap();

        OutputView.printWinningStatistics(map, money.getMoney());
    }
}
