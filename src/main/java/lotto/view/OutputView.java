package lotto.view;

import java.util.List;
import java.util.Map;
import lotto.domain.LottoTicket;
import lotto.domain.LottoTickets;
import lotto.domain.ticketpurchase.PurchasedTickets;
import lotto.domain.ticketresult.Rank;
import lotto.view.printer.InputPrinter;
import lotto.view.printer.OutputPrinter;

public class OutputView {
    private OutputView() {
    }

    public static void printPurchasedLottoTickets(PurchasedTickets purchasedLottoTickets) {
        List<LottoTicket> lottoTickets = purchasedLottoTickets.getTickets();
        OutputPrinter.printCompletedPurchaseGuideMessage(lottoTickets.size());
        for (LottoTicket lottoTicket : lottoTickets) {
            OutputPrinter.printLottoTicketNumbers(lottoTicket);
        }
        InputPrinter.printNewLine();
    }

    public static void printResult(Map<Rank, Integer> result, int purchasePrice) {
        OutputPrinter.printResultTitleMessage();
        int totalPrize = 0;

        for (Rank rank : Rank.values()) {
            totalPrize += rank.getPrizeMoney() * result.get(rank);
            OutputPrinter.printEachNumberMatchedCountMessage(rank, result);
        }

        double profit = (double) totalPrize / (double) purchasePrice;
        OutputPrinter.printProfitMessage(profit);
    }
}
