package lotto.view.output;

import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.Lottos;
import lotto.domain.purchase.PurchaseCount;
import lotto.domain.result.LottoResult;
import lotto.domain.result.Rank;

import java.util.Map;

public class OutputView {
    public static void outputLottoCount(PurchaseCount purchaseCount) {
        System.out.println("수동으로 " + purchaseCount.calculateManualCount() + "장," +
                " 자동으로 " + purchaseCount.calculateAutoCount() + "개를 구매했습니다.");
    }

    public static void outputLottos(Lottos lottos) {
        for (Lotto lotto : lottos.getLottos()) {
            System.out.println(lotto);
        }
    }

    public static void outputResult(LottoResult lottoResult) {
        outputWinMessage();
        outputWinStats(lottoResult.getMap());
        System.out.println("총 수익률은 " + lottoResult.yield() + "% 입니다.");
    }

    private static void outputWinMessage() {
        System.out.println();
        System.out.println("당첨 통계");
        System.out.println("--------");
    }

    private static void outputWinStats(Map<Rank, Integer> rankLinkedHashMap) {
        for (Map.Entry<Rank, Integer> rankEntry : rankLinkedHashMap.entrySet()) {
            outputRankMessage(rankEntry.getKey(), rankEntry.getValue());
        }
    }

    private static void outputRankMessage(Rank rank, int count) {
        if (rank == Rank.MISS) {
            return;
        }
        System.out.print(rank.getRank() + "개 일치");
        if (rank == Rank.SECOND) {
            System.out.print(", 보너스 볼 일치");
        }
        System.out.print("(" + rank.getMoney() + "원) - ");
        System.out.println(count + "개");
    }
}
