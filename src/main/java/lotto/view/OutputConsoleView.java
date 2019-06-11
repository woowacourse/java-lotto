package lotto.view;

import lotto.domain.Lotto;
import lotto.domain.Rank;
import lotto.domain.WinPrize;

import java.util.List;

public class OutputConsoleView implements OutputView {

    private static final String NEW_LINE = "\n";

    @Override
    public void printLottos(final List<Lotto> lottos, int countOfManual, int countOfPurchase) {
        StringBuilder sb = new StringBuilder();
        int countOfAuto = countOfPurchase - countOfManual;
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
        for (final String message : ResultFormat.format(winPrize)) {
            System.out.println(message);
        }
    }

    @Override
    public void printRateOfProfit(final WinPrize winPrize) {
        double m = winPrize.getRateOfProfit();
        System.out.println("총 수익률은 " + m + "%입니다.");
    }
}
