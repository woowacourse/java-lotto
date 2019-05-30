package lotto.view;

import lotto.domain.Lottos;
import lotto.domain.WinningResult;

public class OutputView {
    public static void outputCountOfPurchase(int countOfPurchase) {
        System.out.println(countOfPurchase + "개를 구매하셨습니다.");
    }

    public static void outputLottos(Lottos lottos) {
        System.out.println(lottos);
    }

    public static void outputWinningResult(WinningResult winningResult) {
        outputWinningResultTitle();
        System.out.println(winningResult);
    }

    private static void outputWinningResultTitle() {
        System.out.println("당첨 통계");
        System.out.println("---------");
    }

    public static void outputRevenueRate(double revenueRate) {
        System.out.println("총 수익률은 " + revenueRate + "%입니다.");
    }
}
