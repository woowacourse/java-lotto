package view;

import domain.*;

public class OutputView {
    public static void printBuyTicketCount(int number) {
        System.out.println(String.format("%d", number) + "개를 구매했습니다.");
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
            System.out.println(rankType.getPrintStr() + String.format("%d", lottoResults.getLottoResults().get(rankType)) + "개");
        }
        System.out.println("총 수익률은 " + Money.getProfit(lottoResults.getLottoResults(), money.getMoney()) + "%입니다.");
    }
}