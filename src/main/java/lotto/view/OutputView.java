package lotto.view;

import java.util.Map;
import lotto.domain.LottoMatchType;
import lotto.domain.LottoTicket;
import lotto.domain.PurchasedLottoTickets;
import lotto.view.printer.InputPrinter;
import lotto.view.printer.OutputPrinter;

public class OutputView {
    private OutputView() {
    }

    public static void printPurchasedLottoTickets(PurchasedLottoTickets purchasedLottoTickets) {
        OutputPrinter.printCompletedPurchaseGuideMessage(purchasedLottoTickets.getTickets().size());
        for (LottoTicket lottoTicket : purchasedLottoTickets.getTickets()) {
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
