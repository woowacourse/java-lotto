package lotto.view;

import lotto.domain.ticket.LottoTicket;
import lotto.domain.ticketpurchase.PurchasePrice;
import lotto.domain.ticketpurchase.PurchasedTickets;
import lotto.domain.ticketpurchase.UserPurchase;
import lotto.domain.ticketresult.Rank;
import lotto.view.printer.InputPrinter;
import lotto.view.printer.OutputPrinter;

import java.util.List;
import java.util.Map;

public class OutputView {
    private OutputView() {
    }

    public static void printPurchasedLottoTickets(PurchasedTickets purchasedLottoTickets) {
        List<LottoTicket> lottoTickets = purchasedLottoTickets.getTickets();
        int manualTicketCount = purchasedLottoTickets.getManualTicketCount();
        int autoTicketCount = purchasedLottoTickets.getAutoTicketCount();
        OutputPrinter.printCompletedPurchaseGuideMessage(manualTicketCount, autoTicketCount);
        for (LottoTicket lottoTicket : lottoTickets) {
            OutputPrinter.printLottoTicketNumbers(lottoTicket);
        }
        InputPrinter.printNewLine();
    }

    public static void printResult(Map<Rank, Integer> result, PurchasePrice purchasePrice) {
        OutputPrinter.printResultTitleMessage();
        int totalPrize = 0;

        for (Rank rank : Rank.values()) {
            totalPrize += rank.getPrizeMoney() * result.get(rank);
            OutputPrinter.printEachNumberMatchedCountMessage(rank, result);
        }

        double profit = (double) totalPrize / (double) purchasePrice.getPurchasePrice();
        OutputPrinter.printProfitMessage(profit);
    }
}
