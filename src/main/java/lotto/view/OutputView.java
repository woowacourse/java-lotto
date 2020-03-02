package lotto.view;

import lotto.domain.*;

public class OutputView {
    private OutputView() {
    }

    public static void printLottos(LottoTickets lottoTickets) {
        System.out.printf("%d개를 구매했습니다.", lottoTickets.size());
        for (LottoTicket lottoTicket : lottoTickets.getLottoTickets()) {
            System.out.println(lottoTicket.getNumbers());
        }
    }

    public static void printLottos(LottoTickets manualLottoTickets, LottoTickets randomLottoTickets) {
        System.out.printf("수동으로 %d개, 자동으로 %d개를 구매했습니다.", manualLottoTickets.size(), randomLottoTickets.size());
        for (LottoTicket manualLottoTicket : manualLottoTickets.getLottoTickets()) {
            System.out.println(manualLottoTicket.getNumbers());
        }
        for (LottoTicket randomLottoTicket : randomLottoTickets.getLottoTickets()) {
            System.out.println(randomLottoTicket.getNumbers());
        }
    }

    public static void printRanks(Ranks ranks) {
        System.out.println("당첨 통계");
        System.out.println("-------");

        for (Rank rank : Rank.values()) {
            int matchedCount = rank.getMatchedCount();
            int winningMoney = rank.getWinningMoney().getValue();
            int containingCount = ranks.frequency(rank);
            System.out.printf("%d개 일치 (%d원) - %d개%n", matchedCount, winningMoney, containingCount);
        }
    }

    public static void printProfit(Profit profit) {
        System.out.printf("총 수익률은 %d%%입니다.", profit.getProfitWithoutDecimalPoint());
    }
}
