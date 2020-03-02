package lotto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lotto.controller.LottoManager;
import lotto.model.LottoMerge;
import lotto.model.LottoResult;
import lotto.model.LottoCount;
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
        LottoCount lottoCount = getManualNumber(money.getTotalTicketCount());

        Tickets tickets = new Tickets(getTickets(lottoCount));
        WinLottoNumbers winLottoNumbers = getWinNumbersAndBonusBallNumber();

        LottoResult lottoResult = LottoManager.lotto(tickets, winLottoNumbers);

        printCorrectResults(lottoResult);
        OutputView.printYield(money.getYield(lottoResult));
    }

    private static Money getMoney() {
        OutputView.printInputMoney();
        return new Money(InputView.inputMoney());
    }

    private static LottoCount getManualNumber(int ticketCount) {
        OutputView.printInputManualNumber();
        return new LottoCount(ticketCount, InputView.inputManualCount());
    }

    private static List<Ticket> getTickets(LottoCount lottoCount) {
        List<Ticket> tickets = LottoMerge
            .merge(getManualTickets(lottoCount), getAutoTickets(lottoCount));
        OutputView.printHowManyTicketsPurchase(lottoCount.getManualTicketCount(),
            lottoCount.getAutoTicketCount());
        OutputView.printAutoNumbers(tickets);
        return tickets;
    }

    private static List<Ticket> getManualTickets(LottoCount lottoCount) {
        OutputView.printInputManualLottoNumber();
        ManualTicketsGenerator manualTicketsGenerator = new ManualTicketsGenerator();
        return manualTicketsGenerator.generate(lottoCount.getManualTicketCount());
    }

    private static List<Ticket> getAutoTickets(LottoCount lottoCount) {
        AutoTicketsGenerator autoTicketsGenerator = new AutoTicketsGenerator();
        return autoTicketsGenerator.generate(lottoCount.getAutoTicketCount());
    }

    private static WinLottoNumbers getWinNumbersAndBonusBallNumber() {
        OutputView.printInputWinNumber();
        String winNumber = InputView.inputWinNumbers();
        OutputView.printInputBonusNumber();
        String bonusBallNumber = InputView.inputBonusBall();
        return new WinLottoNumbers(winNumber, bonusBallNumber);
    }

    private static void printCorrectResults(LottoResult lottoResult) {
        OutputView.printResult();
        Arrays.stream(RankType.values())
            .filter(rankType -> rankType != RankType.NONE)
            .forEach(rankType -> OutputView
                .printCorrectResult(rankType.getMatchCount(), rankType.getPrize(),
                    lottoResult.getResultCount(rankType)));
    }
}
