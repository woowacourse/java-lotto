package view;

import domain.*;

public class OutputView {
    public static void printBuyTicketCount(Money money, ManualLottoTicketQuantity manualLottoTicketQuantity) {
        System.out.println("수동으로 "
                + String.format("%d", manualLottoTicketQuantity.getManualLottoTicketQuantity())
                + "장, 자동으로 "
                + String.format("%d", manualLottoTicketQuantity.getAutoLottoTicketQuantity(money))
                + "장을 구매했습니다."
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
            System.out.println(rankType.getPrintStr() + String.format("%d", lottoResults.getLottoResults().get(rankType)) + "개");
        }
        System.out.println("총 수익률은 " + Money.getProfit(lottoResults.getLottoResults(), money.getMoney()) + "%입니다.");
    }
}