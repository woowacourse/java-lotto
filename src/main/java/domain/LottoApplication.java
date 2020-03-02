package domain;

import view.InputView;
import view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class LottoApplication {
    public static void run() {
        Money money = inputMoney();
        LottoTicketCount lottoTicketCount = inputManualLottoTicketCount(money);
        LottoTickets lottoTickets = buyLottoTickets(lottoTicketCount);

        OutputView.printBuyTicketCount(lottoTicketCount);
        OutputView.printLottoTickets(lottoTickets);

        LottoResults lottoResults = LottoManager.match(lottoTickets, inputWinningLottoTicket());

        OutputView.printWinningStatistics(lottoResults, money);
    }


    public static List<String> readManualLottoTickets(ManualCount manualCount) {
        InputView.printInputManualLottoTicket();

        List<String> manualLottoTickets = new ArrayList<>();
        for (int i = 0; i < manualCount.getManualCount(); i++) {
            manualLottoTickets.add(InputView.inputManualLottoTicket());
        }
        return manualLottoTickets;
    }

    public static LottoTickets buyLottoTickets(LottoTicketCount lottoTicketCount) {
        ManualCount manualCount = lottoTicketCount.getManualCount();
        AutoCount autoCount = lottoTicketCount.getAutoCount();

        List<String> manualLottoTickets = readManualLottoTickets(manualCount);

        LottoTickets lottoTickets = LottoManager.generateLottoTickets(manualLottoTickets, autoCount);

        return lottoTickets;
    }

    public static Money inputMoney() {
        return new Money(InputView.inputBuyMoney());
    }

    public static WinningLottoTicket inputWinningLottoTicket() {
        return new WinningLottoTicket(InputView.inputWinningNumber(), InputView.inputBonusNumber());
    }

    public static LottoTicketCount inputManualLottoTicketCount(Money money) {
        return new LottoTicketCount(money, InputView.inputManualLottoTicketCount());
    }
}
