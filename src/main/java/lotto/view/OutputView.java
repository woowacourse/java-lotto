package lotto.view;

import java.util.List;
import java.util.Map;
import lotto.type.LottoMatchType;
import lotto.domain.LottoTicket;
import lotto.domain.ticketpurchase.PurchasedLottoTickets;
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

    public static void printResult(Map<LottoMatchType, Integer> result, int purchasePrice) {
        OutputPrinter.printResultTitleMessage();
        int totalPrize = 0;

        for (LottoMatchType lottoMatchType : LottoMatchType.values()) {
            totalPrize += lottoMatchType.getPrizeMoney() * result.get(lottoMatchType);
            OutputPrinter.printEachNumberMatchedCountMessage(lottoMatchType, result);
        }

        double profit = (double) totalPrize / (double) purchasePrice;
        OutputPrinter.printProfitMessage(profit);
    }
}
