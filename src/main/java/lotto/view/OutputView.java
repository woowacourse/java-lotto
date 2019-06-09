package lotto.view;

import lotto.domain.LottoRank;
import lotto.domain.Lottos;
import lotto.domain.WinningResult;

import java.util.Map;

public class OutputView {
    public static void outputCountOfPurchase(int countOfPurchase) {
        System.out.println(countOfPurchase + "개를 구매하셨습니다.");
    }

    public static void outputLottos(Lottos lottos) {
        System.out.println(lottos);
    }

    public static void outputWinningResult(Map<LottoRank, Integer> result) {
        outputWinningResultTitle();
        StringBuilder sb = new StringBuilder();
        for (LottoRank lottoRank : result.keySet()) {
            sb.append(lottoRank.getCountOfMatch() + "개 일치 ");
            String second = lottoRank.equals(LottoRank.SECOND) ? ", 보너스볼 일치" : "";
            sb.append(second + "(" + lottoRank.getWinningAmount() + "원) - ");
            sb.append(result.get(lottoRank) + "개\n");
        }
        System.out.println(sb.toString());
    }

    private static void outputWinningResultTitle() {
        System.out.println("당첨 통계");
        System.out.println("---------");
    }

    public static void outputRevenueRate(double revenueRate) {
        System.out.println("총 수익률은 " + revenueRate + "%입니다.");
    }
}
