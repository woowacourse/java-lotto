package domain;

import view.InputView;
import view.OutputView;

public class LottoApplication {
    public static void run() {
        Money money = inputMoney();
        LottoTickets lottoTickets = generateLottoTicketsByMoney(money);
        LottoTicketsStatus(money, lottoTickets);
        LottoTicketsResult(money, lottoTickets);
    }

    public static void LottoTicketsResult(Money money, LottoTickets lottoTickets) {
        OutputView.printWinningStatistics(LottoManager.match(lottoTickets, inputWinningLottoTicket()), money);

    }

    public static void LottoTicketsStatus(Money money, LottoTickets lottoTickets) {
        OutputView.printBuyTicketCount(money.countLottoTicket());
        OutputView.printLottoTickets(lottoTickets);
    }

    public static LottoTickets generateLottoTicketsByMoney(Money money) {
        return new LottoTickets(LottoNumbersGenerator.generateLottoTickets(money.countLottoTicket()));
    }

    public static Money inputMoney() {
        return new Money(InputView.inputBuyMoney());
    }

    public static WinningLottoTicket inputWinningLottoTicket() {
        return new WinningLottoTicket(InputView.inputWinningNumber(), InputView.inputBonusNumber());
    }
}
