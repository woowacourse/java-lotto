package lotto.view;

import java.util.List;
import lotto.domain.LottoTicket;
import lotto.domain.ticketpurchase.LottoTickets;
import lotto.domain.ticketresult.LottoResult;
import lotto.type.LottoMatchType;
import lotto.view.printer.InputPrinter;
import lotto.view.printer.OutputPrinter;

public class OutputView {
    private OutputView() {
    }

    public static void printPurchasedLottoTickets(LottoTickets purchasedLottoTickets) {
        List<LottoTicket> lottoTickets = purchasedLottoTickets.getTickets();
        OutputPrinter.printCompletedPurchaseGuideMessage(lottoTickets.size());
        for (LottoTicket lottoTicket : lottoTickets) {
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
