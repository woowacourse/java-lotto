package lotto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lotto.controller.LottoManager;
import lotto.model.LottoResultCount;
import lotto.model.ManualCount;
import lotto.model.ManualTicketsGenerator;
import lotto.model.Money;
import lotto.model.RankType;
import lotto.model.Ticket;
import lotto.model.Tickets;
import lotto.model.AutoTicketsGenerator;
import lotto.model.WinLottoNumbers;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoApplication {

    public static void main(String[] args) {
        Money money = getMoney();
        ManualCount manualCount = getManualNumber(money.getTicketCount());

        Tickets tickets = new Tickets(getTickets(manualCount.getManualCount(),
            money.getTicketCount() - manualCount.getManualCount()));
        WinLottoNumbers winLottoNumbers = getWinNumbersAndBonusBallNumber();

        LottoResultCount lottoResultCount = LottoManager.lotto(tickets, winLottoNumbers);

        printCorrectResults(lottoResultCount);
        OutputView.printYield(money.getYield(lottoResultCount));
    }

    private static Money getMoney() {
        OutputView.printInputMoney();
        return new Money(InputView.input());
    }

    private static ManualCount getManualNumber(int ticketCount) {
        OutputView.printInputManualNumber();
        return new ManualCount(InputView.input(), ticketCount);
    }

    private static List<Ticket> getTickets(int manualCount, int autoCount) {
        List<Ticket> tickets = new ArrayList<>();
        tickets.addAll(getManualTickets(manualCount));
        tickets.addAll(getAutoTickets(autoCount));
        OutputView.printHowManyTicketsPurchase(manualCount, autoCount);
        OutputView.printAutoNumbers(tickets);
        return tickets;
    }

    private static List<Ticket> getManualTickets(int manualCount) {
        OutputView.printInputManualLottoNumber();
        ManualTicketsGenerator manualTicketsGenerator = new ManualTicketsGenerator();
        return manualTicketsGenerator.generate(manualCount);
    }

    private static List<Ticket> getAutoTickets(int autoCount) {
        AutoTicketsGenerator autoTicketsGenerator = new AutoTicketsGenerator();
        return autoTicketsGenerator.generate(autoCount);
    }

    private static WinLottoNumbers getWinNumbersAndBonusBallNumber() {
        OutputView.printInputWinNumber();
        String winNumber = InputView.input();
        OutputView.printInputBonusNumber();
        String bonusBallNumber = InputView.input();
        return new WinLottoNumbers(winNumber, bonusBallNumber);
    }

    private static void printCorrectResults(LottoResultCount lottoResultCount) {
        OutputView.printResult();
        Arrays.stream(RankType.values())
            .filter(rankType -> rankType != RankType.NONE)
            .forEach(rankType -> OutputView
                .printCorrectResult(rankType.getMatchCount(), rankType.getPrize(),
                    lottoResultCount.getResultCount(rankType)));
    }
}
