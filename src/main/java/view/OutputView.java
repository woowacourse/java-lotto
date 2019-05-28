package view;

import model.LottoRank;
import model.LottoResult;
import model.Lottos;

public class OutputView {
    public static void printPurchaseAmount(int manual, int auto) {
        System.out.println("\n수동으로 " + manual + "장, 자동으로 " + auto + "개를 구매했습니다.");
    }

    public static void printLottos(Lottos lottos) {
        lottos.forEach(lotto -> System.out.println(lotto));
    }

    public static void printResult(LottoResult result) {
        System.out.println("\n당첨 통계\n---------");
        result.forEach(x -> {
            System.out.println(
                    x.getKey().getMatchingNumbers()
                    + ((x.getKey().equals(LottoRank.SECOND)) ? "개 일치, 보너스 볼 일치 (" : "개 일치 (")
                    + x.getKey().getPrize()
                    + "원)- "
                    + x.getValue()
                    + "개"
            );
        });
        System.out.format("총 수익률은 %d%%입니다.", Math.round(result.getEarningRate()));
    }
}
