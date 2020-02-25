package view;

import domain.LottoTicket;
import domain.Money;
import domain.RankType;

import java.util.List;
import java.util.Map;

public class OutputView {
    public static void printBuyTicketCount(int number) {
        System.out.println(String.format("%d", number) + "개를 구매했습니다.");
    }

    public static void printLottoTickets(List<LottoTicket> lottoTickets) {
        lottoTickets.forEach(lottoTicket -> {
            System.out.println(lottoTicket.getLottoTicket().toString());
        });
    }

    public static void printWinningStatistics(Map<RankType, Integer> winningCountMap, int money) {
        System.out.println("당첨 통계");
        System.out.println("--------");

        for(RankType rankType : RankType.values()) {
            System.out.println(rankType.getPrintStr() + String.format("%d", winningCountMap.get(rankType)) + "개");
        }
        System.out.println("총 수익률은 " + Money.getProfit(winningCountMap, money) + "%입니다.");
    }
}