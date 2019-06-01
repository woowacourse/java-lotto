package lotto.view;

import lotto.model.Lotto;
import lotto.model.LottoResult;
import lotto.model.Lottos;
import lotto.model.Prize;

public class OutputView {


    public static void printLottos(Lottos manualLottos, Lottos automaticLottos) {
        System.out.println("수동으로 " + manualLottos.size() + " 자동으로 " + automaticLottos.size() + " 를 구매했습니다!");
        printLottos(manualLottos);
        printLottos(automaticLottos);
    }

    private static void printLottos(Lottos lottos) {
        for (Lotto lotto : lottos.getLottos()) {
            System.out.println(lotto);
        }
    }

    public static void printStatistics(LottoResult lottoResult) {
        System.out.println("당첨통계");
        System.out.println("---------");
        for (Prize prize: Prize.values() ) {
            System.out.println(prize.getMatchCount() + "개 일치" + "(" + prize.getPrizeMoney() + ")" + " - " + lottoResult.getCount(prize)+ "개");
        }
        System.out.println("총 수익률은 " + lottoResult.calculateProfitRate() + "입니다");
    }
}
