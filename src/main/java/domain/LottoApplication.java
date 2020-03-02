package domain;

import view.InputView;
import view.OutputView;

public class LottoApplication {
    public static void run() {
        Money money = inputMoney();
        ManualLottoTicketQuantity manualLottoTicketQuantity = inputManualLottoTicketCount(money);

        buyManualLottoTickets(money, manualLottoTicketQuantity);
        LottoTickets lottoTickets = buyAutoLottoTickets(money, manualLottoTicketQuantity);

        LottoResults lottoResults = LottoManager.match(lottoTickets, inputWinningLottoTicket());

        OutputView.printWinningStatistics(lottoResults, money);
    }

    public static void buyManualLottoTickets(Money money, ManualLottoTicketQuantity manualLottoTicketQuantity) {
        InputView.inputManualLottoTicket(manualLottoTicketQuantity);
        OutputView.printBuyTicketCount(money, manualLottoTicketQuantity);
    }

    public static LottoTickets buyAutoLottoTickets(Money money, ManualLottoTicketQuantity manualLottoTicketQuantity) {
        LottoTickets lottoTickets = generateLottoTicketsByMoney(money, manualLottoTicketQuantity);
        OutputView.printLottoTickets(lottoTickets);
        return lottoTickets;
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
