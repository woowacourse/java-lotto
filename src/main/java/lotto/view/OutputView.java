package lotto.view;

import lotto.domain.Lotto;
import lotto.domain.WinningResult;

import java.util.List;

public class OutputView {
    public static void outputCountOfPurchase(int countOfPurchase) {
        System.out.println(countOfPurchase + "개를 구매하셨습니다.");
    }

    public static void outputLottos(List<Lotto> lottos) {
        for (Lotto lotto : lottos) {
            System.out.println(lotto + "\n");
        }
    }

    public static void outputWinningResult(WinningResult winningResult){
        outputWinningResultTitle();
        System.out.println(winningResult);
    }

    private static void outputWinningResultTitle(){
        System.out.println("당첨 통계");
        System.out.println("---------");
    }

    public static void outputRevenueRate(double revenueRate) {
        System.out.println("총 수익률은 "+revenueRate+"%입니다.");
    }
}
