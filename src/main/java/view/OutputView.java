package view;

import static util.constant.Message.*;
import static util.constant.Values.PROFIT_DAMAGE_LIMIT;

import domain.LottoRank;
import java.util.Map;

public class OutputView {

    public void displayLottos(int totalLotto, String result) {
        System.out.printf(TOTAL_LOTTO_FORMAT, totalLotto);
        System.out.println(result);
    }

    public void displayResult(Map<LottoRank, Integer> lottoResult) {
        System.out.println(STATISTICS_START_MESSAGE);
        System.out.println(DIVIDER_LINE);
        for (LottoRank lottoRank : lottoResult.keySet()) {
            if (lottoRank == lottoRank.BOOM) {
                continue;
            }
            System.out.printf(STATISTICS_FORMAT, lottoRank.toString(), lottoResult.get(lottoRank));
        }
    }

    public void displayProfit(double profit) {
        System.out.printf(PROFIT_FORMAT, profit);
        if (profit < PROFIT_DAMAGE_LIMIT) {
            System.out.println(PROFIT_DAMAGE_MESSAGE);
        }
    }
}
