package lotto.view;

import lotto.domain.*;

public class OutputView {
    private OutputView() {
    }

    public static void printLottos(TicketCounts ticketCounts, LottoTickets lottoTickets) {
        System.out.printf("수동으로 %d개, 자동으로 %d개를 구매했습니다.", ticketCounts.getManualTicketCount(), ticketCounts.getAutoTicketCount());
        for (LottoTicket lottoTicket : lottoTickets.getLottoTickets()) {
            System.out.println(lottoTicket.getNumbers());
        }
    }

    public static void printRanks(Ranks ranks) {
        System.out.println("당첨 통계");
        System.out.println("-------");

        for (Rank rank : Rank.winningValues()) {
            int matchedCount = rank.getMatchedCount();
            int winningMoney = rank.getWinningMoney().getValue();
            int containingCount = ranks.frequency(rank);
            System.out.printf("%d개 일치 (%d원) - %d개%n", matchedCount, winningMoney, containingCount);
        }
    }

    public static void printProfit(double profit) {
        System.out.printf("총 수익률은 %d%%입니다.", (int) profit);
    }
}
