package lotto.view;

import lotto.domain.LottoTicket;
import lotto.domain.Money;
import lotto.domain.Rank;

import java.util.List;

public class OutputView {

    private OutputView() {
    }

    public static void printLottos(List<LottoTicket> lottoTickets) {
        System.out.println(String.format("%d개를 구매했습니다.", lottoTickets.size()));
        for (LottoTicket lottoTicket : lottoTickets) {
            System.out.println(lottoTicket.getNumbers());
        }
    }

    public static void printResult(List<Rank> ranks) {
        System.out.println("당첨 통계");
        System.out.println("-------");

        for (Rank rank : Rank.values()) {
            int matchedCount = rank.getMatchedCount();
            Money winningMoney = rank.getWinningMoney();
            int containingCount = rank.getContainingCount(ranks);
            System.out.println(String.format("%d개 일치 (%d원) - %d개",matchedCount, winningMoney.getValue(), containingCount));
        }
    }

    public static void printProfit(Money purchaseMoney, List<Rank> ranks) {
        Money totalWinningMoney = Rank.sumWinningMoney(ranks);
        int profit = totalWinningMoney.calculateProfit(purchaseMoney);
        System.out.println(String.format("총 수익률은 %d%%입니다.", profit));
    }
}
