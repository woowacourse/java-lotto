package lotto.view.output;

import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.Lottos;
import lotto.domain.purchase.PurchaseCount;
import lotto.domain.result.LottoResult;
import lotto.domain.result.LottoRank;

import java.util.Map;

public class OutputView {
    public static void outputLottoCount(PurchaseCount purchaseCount) {
        System.out.println("수동으로 " + purchaseCount.getManualCount() + "장," +
                " 자동으로 " + purchaseCount.getAutoCount() + "개를 구매했습니다.");
    }

    public static void outputLottos(Lottos lottos) {
        for (Lotto lotto : lottos.getLottos()) {
            System.out.println(lotto.getLottoNumbers().toString());
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

    private static void outputWinStats(Map<LottoRank, Integer> rankLinkedHashMap) {
        for (Map.Entry<LottoRank, Integer> rankEntry : rankLinkedHashMap.entrySet()) {
            outputRankMessage(rankEntry.getKey(), rankEntry.getValue());
        }
    }

    private static void outputRankMessage(LottoRank lottoRank, int count) {
        if (lottoRank == LottoRank.MISS) {
            return;
        }
        System.out.print(lottoRank.getRank() + "개 일치");
        if (lottoRank == LottoRank.SECOND) {
            System.out.print(", 보너스 볼 일치");
        }
        System.out.print("(" + lottoRank.getMoney() + "원) - ");
        System.out.println(count + "개");
    }
}
