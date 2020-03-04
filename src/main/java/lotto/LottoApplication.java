package lotto;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lotto.controller.LottoManager;
import lotto.model.LottoMaker;
import lotto.model.LottoResult;
import lotto.model.LottoCount;
import lotto.model.Money;
import lotto.model.RankType;
import lotto.model.Ticket;
import lotto.model.Tickets;
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
        return new Money(InputView.input());
    }

    private static LottoCount getManualNumber(int ticketCount) {
        OutputView.printInputManualNumber();
        return new LottoCount(ticketCount, InputView.input());
    }

    private static List<Ticket> getTickets(LottoCount lottoCount) {
        OutputView.printInputManualLottoNumber();
        List<Ticket> tickets = LottoMaker.create(getInputManualTicket(lottoCount), lottoCount);
        OutputView.printHowManyTicketsPurchase(lottoCount.getManualTicketCount(),
            lottoCount.getAutoTicketCount());
        OutputView.printAutoNumbers(tickets);
        return tickets;
    }

    private static List<String> getInputManualTicket(LottoCount lottoCount) {
        List<String> manualTickets = new ArrayList<>();
        for (int i = 0; i < lottoCount.getManualTicketCount(); i++) {
            manualTickets.add(InputView.input());
        }
        return manualTickets;
    }

    private static WinLottoNumbers getWinNumbersAndBonusBallNumber() {
        OutputView.printInputWinNumber();
        String winNumber = InputView.input();
        OutputView.printInputBonusNumber();
        String bonusBallNumber = InputView.input();
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
