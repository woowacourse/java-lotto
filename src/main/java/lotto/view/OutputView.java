package lotto.view;

import lotto.domain.LottoTicket;
import lotto.domain.Money;
import lotto.domain.Profit;
import lotto.domain.Rank;

import java.util.List;

public class OutputView {
    private OutputView() {
    }

    public static void printLottos(List<LottoTicket> lottoTickets) {
        System.out.printf("%d개를 구매했습니다.", lottoTickets.size());
        for (LottoTicket lottoTicket : lottoTickets) {
            System.out.println(lottoTicket.getNumbers());
        }
    }

    public static void printLottos(List<LottoTicket> manualLottoTickets, List<LottoTicket> randomLottoTickets) {
        System.out.printf("수동으로 %d개, 자동으로 %d개를 구매했습니다.", manualLottoTickets.size(), randomLottoTickets.size());
        for (LottoTicket manualLottoTicket : manualLottoTickets) {
            System.out.println(manualLottoTicket.getNumbers());
        }
        for (LottoTicket randomLottoTicket : randomLottoTickets) {
            System.out.println(randomLottoTicket.getNumbers());
        }
    }

    public static void printResult(List<Rank> ranks) {
        System.out.println("당첨 통계");
        System.out.println("-------");

        for (Rank rank : Rank.values()) {
            int matchedCount = rank.getMatchedCount();
            Money winningMoney = rank.getWinningMoney();
            int containingCount = rank.getContainingCount(ranks);
            System.out.printf("%d개 일치 (%d원) - %d개%n", matchedCount, winningMoney.getValue(), containingCount);
        }
    }

    public static void printProfit(Profit profit) {
        System.out.println(String.format("총 수익률은 %d%%입니다.", profit.getProfitWithoutDecimalPoint()));
    }
}
