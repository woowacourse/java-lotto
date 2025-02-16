package view;

import domain.LottoRank;
import java.util.Map;

public class OutputView {

    private final String TOTAL_LOTTO_FORMAT = "%d개를 구매했습니다.\n";
    private final String STATICS_FORMAT = "%s- %d개\n";
    private final String PROFIT_FORMAT = "총 수익률은 %.2f입니다.";

    public void displayLottos(int totalLotto, String result) {
        System.out.printf(TOTAL_LOTTO_FORMAT, totalLotto);
        System.out.println(result);
    }

    public void displayResult(Map<LottoRank, Integer> lottoResult) {
        System.out.println("당첨 통계");
        System.out.println("---------");
        for (LottoRank lottoRank : lottoResult.keySet()) {
            if (lottoRank == lottoRank.BOOM) {
                continue;
            }
            System.out.printf(STATICS_FORMAT, lottoRank.toString(), lottoResult.get(lottoRank));
        }
    }

    public void displayProfit(double profit) {
        System.out.printf(PROFIT_FORMAT, profit);
        if (profit < 1.0) {
            System.out.println("(기준이 1이기 때문에 결과적으로 손해라는 의미임)");
        }
    }
}
