package lotto.view;

import lotto.model.*;

public class OutputView {


    public static void printLottos(Money money, ManualLottoCount manualLottoCount, Lottos lottos) {
        int manualCount = manualLottoCount.getCount();
        System.out.println("수동으로 " + manualCount + " 자동으로 " + money.calculateAutomaticLottoCount(manualCount) + " 를 구매했습니다!");
        printLottos(lottos);
    }

    private static void printLottos(Lottos lottos) {
        for (Lotto lotto : lottos.getLottos()) {
            System.out.println(lotto);
        }
    }

    public static void printStatistics(LottoResult lottoResult) {
        System.out.println("당첨통계");
        System.out.println("---------");
        for (Prize prize : Prize.values()) {
            System.out.println(prize.getMatchCount() + "개 일치" + "(" + prize.getPrizeMoney() + ")" + " - " + lottoResult.getCount(prize) + "개");
        }
        System.out.println("총 수익률은 " + lottoResult.calculateProfitRate() + "입니다");
    }
}
