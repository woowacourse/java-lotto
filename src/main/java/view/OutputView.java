package view;

import lotto.Lotto;
import lotto.Rank;

import java.util.List;

public class OutputView {
    public static void printLottos(List<Lotto> lottos) {
        System.out.println(String.format("%d개를 구매했습니다.", lottos.size()));
        for (Lotto lotto : lottos) {
            System.out.println(lotto.getNumbers());
        }
    }

    public static void printResult(List<Rank> ranks) {
        System.out.println("당첨 통계");
        System.out.println("-------");

        for (Rank rank : Rank.values()) {
            int matchedCount = rank.getMatchedCount();
            int winningMoney = rank.getWinningMoney();
            int containingCount = rank.getContainingCount(ranks);
            System.out.println(String.format("%d개 일치 (%d원) - %d개",matchedCount, winningMoney, containingCount));
        }
    }

    public static void printProfit(int purchaseMoney, List<Rank> ranks) {
        double totalWinningMoney = Rank.sumWinningMoney(ranks);
        double profit = totalWinningMoney / purchaseMoney;
        System.out.println(String.format("총 수익률은 %d%입니다.", profit));
    }
}
