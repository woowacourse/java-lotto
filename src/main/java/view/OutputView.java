package view;

import domain.LottoCalculator;
import domain.LottoTicket;
import domain.LottoType;

import java.util.HashMap;
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

    public static void printWinningStatistics(HashMap<String, Integer> map, int money) {
        System.out.println("당첨 통계");
        System.out.println("--------");

        for (Map.Entry<String, Integer> entry: map.entrySet()) {
            System.out.println(LottoType.valueOf(entry.getKey()).getPrintStr() + String.format("%d", entry.getValue()) + "개");
        }

        System.out.println("총 수익률은 " + LottoCalculator.getProfit(map, money) + "%입니다.");
    }

}
