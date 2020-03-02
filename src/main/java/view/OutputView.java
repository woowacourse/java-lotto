package view;

import domain.*;

public class OutputView {
    public static void printBuyTicketCount(LottoTicketCount lottoTicketCount) {
        System.out.println(
                String.format("수동으로 %d장, 자동으로 %d장을 구매했습니다.",
                lottoTicketCount.getManualCount().getManualCount(),
                lottoTicketCount.getAutoCount().getAutoCount()
                )
        );
    }

    public static void printLottoTickets(LottoTickets lottoTickets) {
        lottoTickets.getLottoTickets().forEach(lottoTicket -> {
            System.out.println(lottoTicket.getLottoTicket().toString());
        });
    }

    public static void printWinningStatistics(LottoResults lottoResults, Money money) {
        System.out.println("당첨 통계");
        System.out.println("--------");

        for (RankType rankType : RankType.values()) {
            System.out.println(rankType.print() + String.format("%d", lottoResults.getLottoResults().get(rankType)) + "개");
        }
        System.out.println("총 수익률은 " + Money.calculateProfit(lottoResults.getLottoResults(), money.getMoney()) + "%입니다.");
    }
}