package lotto.view;

import java.util.List;
import lotto.domain.LottoTicket;
import lotto.domain.ticketgenerator.AllPurchasedLottoTickets;
import lotto.domain.ticketpurchase.LottoTickets;
import lotto.domain.ticketpurchase.UserPurchase;
import lotto.domain.ticketresult.LottoResult;
import lotto.type.LottoMatchType;
import lotto.view.printer.InputPrinter;
import lotto.view.printer.OutputPrinter;

public class OutputView {
    private OutputView() {
    }

    public static void printAllLottoTickets(LottoTickets lottoTickets, UserPurchase userPurchase) {
        OutputPrinter.printMessageOfCompletedPurchase(userPurchase);
        for (LottoTicket lottoTicket : lottoTickets.getTickets()) {
            OutputPrinter.printLottoTicketNumbers(lottoTicket);
        }
        InputPrinter.printNewLine();
    }

    public static void printResult(LottoResult lottoResult) {
        OutputPrinter.printResultTitleMessage();
        for (LottoMatchType lottoMatchType : LottoMatchType.values()) {
            int countOfMatchedNumbers
                = lottoResult.getCountOfMatchedNumbersOfSpecificType(lottoMatchType);
            OutputPrinter.printEachNumberMatchedCountMessage(lottoMatchType, countOfMatchedNumbers);
        }
        OutputPrinter.printProfitMessage(lottoResult.getProfit());
    }
}
