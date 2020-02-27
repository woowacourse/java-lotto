package domain;

import view.InputView;
import view.OutputView;

public class LottoApplication {
    public static void run() {
        Money money = inputMoney();
        ManualLottoTicketQuantity manualLottoTicketQuantity = inputManualLottoTicketCount(money);
        LottoTicketsCount(money, manualLottoTicketQuantity);
        LottoTickets lottoTickets = generateLottoTicketsByMoney(money, manualLottoTicketQuantity);
        LottoTicketsStatus(lottoTickets);
        LottoTicketsResult(money, lottoTickets);
    }

    public static void LottoTicketsResult(Money money, LottoTickets lottoTickets) {
        OutputView.printWinningStatistics(LottoManager.match(lottoTickets, inputWinningLottoTicket()), money);
    }

    public static void LottoTicketsCount(Money money, ManualLottoTicketQuantity manualLottoTicketQuantity) {
        InputView.inputManualLottoTicket(manualLottoTicketQuantity);
        OutputView.printBuyTicketCount(money, manualLottoTicketQuantity);
    }

    public static void LottoTicketsStatus(LottoTickets lottoTickets) {
        OutputView.printLottoTickets(lottoTickets);

    }

    public static LottoTickets generateLottoTicketsByMoney(Money money, ManualLottoTicketQuantity manualLottoTicketQuantity) {
        return new LottoTickets(LottoTicketsGenerator.generateAutoLottoTickets(manualLottoTicketQuantity.getAutoLottoTicketQuantity(money)));
    }

    public static Money inputMoney() {
        return new Money(InputView.inputBuyMoney());
    }

    public static WinningLottoTicket inputWinningLottoTicket() {
        return new WinningLottoTicket(InputView.inputWinningNumber(), InputView.inputBonusNumber());
    }

    public static ManualLottoTicketQuantity inputManualLottoTicketCount(Money money) {
        return new ManualLottoTicketQuantity(money, InputView.inputBuyManualLottoTicketCount());
    }
}
