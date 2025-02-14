package dto;

import domain.LottoMatch;
import java.util.Map;

public class LottoResultDTO {

    private final String STATICS_FORMAT = "%s- %d개\n";
    private final String PROFIT_FORMAT = "총 수익률은 %.2f입니다.";

    public void displayResult(Map<LottoMatch, Integer> lottoResult) {
        System.out.println("당첨 통계");
        System.out.println("---------");
        for (LottoMatch lottoMatch : lottoResult.keySet()) {
            if (lottoMatch == lottoMatch.DEFUALT_MATCH) {
                continue;
            }
            System.out.printf(STATICS_FORMAT, lottoMatch.toString(), lottoResult.get(lottoMatch));
        }
    }

    public void displayProfit(double profit) {
        System.out.printf(PROFIT_FORMAT, profit);
        if (profit < 1.0) {
            System.out.println("(기준이 1이기 때문에 결과적으로 손해라는 의미임)");
        }
    }

}
