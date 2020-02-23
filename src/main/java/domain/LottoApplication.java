package domain;

import view.InputView;
import view.OutputView;

public class LottoApplication {
    public static void run() {
        Money money = inputMoney();
        LottoTickets lottoTickets = generateLottoTicketsByMoney(money);
        OutputView.printBuyTicketCount(money.calculateLottoTicket());
        OutputView.printLottoTickets(lottoTickets.getLottoTickets());
        OutputView.printWinningStatistics(LottoManager.match(lottoTickets, inputWinningLottoTicket()), money.getMoney());
    }

    public static LottoTickets generateLottoTicketsByMoney(Money money) {
        return new LottoTickets(LottoNumbersGenerator.generateLottoTickets(money.calculateLottoTicket()));
    }

    public static Money inputMoney() {
        return new Money(InputView.inputBuyMoney());
    }

    public static WinningLottoTicket inputWinningLottoTicket() {
        return new WinningLottoTicket(InputView.inputWinningNumber(), InputView.inputBonusNumber());
    }
}
