package lotto.view;

import lotto.domain.Lotto;
import lotto.domain.Money;
import lotto.domain.Rank;
import lotto.domain.WinPrize;

import java.util.List;

public class OutputConsoleView implements OutputView {

    private static final String NEW_LINE = "\n";

    @Override
    public void printLottos(final List<Lotto> lottos, int countOfManual, long countOfPurchase) {
        StringBuilder sb = new StringBuilder();
        long countOfAuto = countOfPurchase - countOfManual;
        sb.append("수동으로 " + countOfManual + "장, 자동으로 " + countOfAuto + "개를 구매했습니다.").append(NEW_LINE);
        for (Lotto lotto : lottos) {
            sb.append(lotto).append(NEW_LINE);
        }
        System.out.println(sb.toString());
    }

    @Override
    public void printResult(final WinPrize winPrize) {
        System.out.println("당첨 통계");
        System.out.println("---------");
        for (Rank rank : Rank.values()) {
            printWinPrice(rank, winPrize);
        }
    }

    private void printWinPrice(final Rank rank, final WinPrize winPrize) {
        if (rank == Rank.MISS)
            return;

        System.out.println(String.format("%d개 일치%s(%d원)- %d개",
                rank.getCountOfMatch(),
                rank.equals(Rank.SECOND) ? ", 보너스 볼 일치" : " ",
                rank.getPrize(),
                winPrize.getWinCount(rank)));
    }

    @Override
    public void printRateOfProfit(final Money money, final WinPrize winPrize) {
        System.out.println("총 수익률은 " + winPrize.getRateOfProfit(money.value()) + "%입니다.");
    }
}
