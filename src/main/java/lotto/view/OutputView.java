package lotto.view;

import java.util.List;
import lotto.domain.LottoTicket;
import lotto.domain.ticketpurchase.PurchasedLottoTickets;
import lotto.domain.ticketresult.LottoResult;
import lotto.type.LottoMatchType;
import lotto.view.printer.InputPrinter;
import lotto.view.printer.OutputPrinter;

public class OutputView {
    private OutputView() {
    }

    public static void printPurchasedLottoTickets(PurchasedLottoTickets purchasedLottoTickets) {
        List<LottoTicket> lottoTickets = purchasedLottoTickets.getTickets();
        OutputPrinter.printCompletedPurchaseGuideMessage(lottoTickets.size());
        for (LottoTicket lottoTicket : lottoTickets) {
            OutputPrinter.printLottoTicketNumbers(lottoTicket);
        }
        InputPrinter.printNewLine();
    }

    public static void printResult(LottoResult lottoResult, int purchasePrice) {
        OutputPrinter.printResultTitleMessage();
        int totalPrize = 0;
        for (LottoMatchType lottoMatchType : LottoMatchType.values()) {
            int countOfMatchedNumbers
                = lottoResult.getCountOfMatchedNumbersOfSpecificType(lottoMatchType);
            totalPrize += lottoMatchType.getPrizeMoney() * countOfMatchedNumbers;
            OutputPrinter.printEachNumberMatchedCountMessage(lottoMatchType, countOfMatchedNumbers);
        }
        OutputPrinter.printProfitMessage((double) totalPrize / (double) purchasePrice);
    }
}
