package lotto.view;

import lotto.domain.LottoTicket;
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
        OutputPrinter.printManualAndAutoTicketsSizeMessage(userPurchase);
        for (LottoTicket lottoTicket : lottoTickets.getAll()) {
            OutputPrinter.printOneLottoTicketNumbers(lottoTicket);
        }
        InputPrinter.printNewLine();
    }

    public static void printResult(LottoResult lottoResult) {
        OutputPrinter.printResultTitle();
        for (LottoMatchType lottoMatchType : LottoMatchType.values()) {
            int matchedTypeCount = lottoResult.getMatchTypeCount(lottoMatchType);
            OutputPrinter.printLottoMatchTypeCountMessage(lottoMatchType, matchedTypeCount);
        }
        OutputPrinter.printProfitMessage(lottoResult.getProfit());
    }
}
