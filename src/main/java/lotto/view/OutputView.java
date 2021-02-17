package lotto.view;

import java.util.Map;
import java.util.stream.Collectors;
import lotto.domain.LottoMatchType;
import lotto.domain.LottoTicket;
import lotto.domain.PurchasedLottoTickets;

public class OutputView {
    private OutputView() {
    }

    public static void printPurchasedLottoTickets(PurchasedLottoTickets purchasedLottoTickets) {
        System.out.printf("%d개를 구매했습니다.\n", purchasedLottoTickets.getTickets().size());
        for (LottoTicket lottoTicket : purchasedLottoTickets.getTickets()) {
            System.out.println("[" +
                lottoTicket.getLottoTicketNumbers().stream()
                    .map(lottoNumber -> Integer.toString(lottoNumber.getNumber()))
                    .collect(Collectors.joining(", "))
                + "]"
            );
        }
    }

    public static void printResult(Map<LottoMatchType, Integer> result, int purchasePrice) {
        System.out.println("당첨 통계");
        System.out.println("---------");

        int totalPrize = 0;

        for (LottoMatchType lottoMatchType : LottoMatchType.values()) {
            totalPrize += lottoMatchType.getPrizeMoney() * result.get(lottoMatchType);
            System.out.printf(lottoMatchType.getMatchCountMessage() + "\n",
                lottoMatchType.getCountMatchedNumbers());
        }
        double profit = (double) totalPrize / (double) purchasePrice;
        System.out.printf("총 수익률은 %f입니다.\n", profit);
    }
}
