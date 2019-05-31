package lotto.view;

import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.LottoResult;
import lotto.domain.lotto.Lottos;
import lotto.domain.money.Prize;

public class OutputView {


    public static void printLottos(Lottos manualLottos, Lottos automaticLottos) {
        System.out.println("수동으로 " + manualLottos.size() + "자동으로 " + automaticLottos.size() + " 를 구매했습니다!");

        for (Lotto lotto : manualLottos.getLottos()) {
            printLotto(lotto);
        }
        for (Lotto lotto : automaticLottos.getLottos()) {
            printLotto(lotto);
        }

    }

    private static void printLotto(Lotto lotto) {
        System.out.println(lotto.toString());
    }

    public static void printStatistics(LottoResult lottoResult) {
        System.out.println("당첨통계");
        System.out.println("---------");
        for (Prize prize : Prize.values()) {
            System.out.println(prize.getMatchCount() + "개 일치" + "(" + prize.getPrizeMoney() + ")" + " - " + lottoResult.getCount(prize) + "개");
        }
        System.out.println("총 수익률은 " + lottoResult.getPercentage() + "입니다");
    }
}
