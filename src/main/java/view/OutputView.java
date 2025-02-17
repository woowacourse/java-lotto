package view;

import static util.constant.Message.*;
import static util.constant.Values.PROFIT_DAMAGE_LIMIT;

import domain.Lotto;
import domain.LottoRank;
import java.util.List;
import java.util.Map;

public class OutputView {

    public void displayLottos(int totalLotto, List<Lotto> lottos) {
        System.out.printf(TOTAL_LOTTO_FORMAT, totalLotto);

        for(Lotto lotto : lottos) {
            System.out.println(lotto.getNumbers());
        }
    }

    public void displayResult(Map<LottoRank, Integer> lottoResult) {
        System.out.println(STATISTICS_START_MESSAGE);
        System.out.println(DIVIDER_LINE);

        lottoResult.keySet()
            .stream()
            .filter(lottoRank -> lottoRank != LottoRank.BOOM)
            .forEach(lottoRank -> {
                String rankInfo = formatRankInfo(lottoRank);
                System.out.printf(STATISTICS_FORMAT, rankInfo, lottoResult.get(lottoRank));
            });
    }

    private String formatRankInfo(LottoRank lottoRank) {
        String rankInfo = String.format(STATISTICS_MATCH_FORMAT, lottoRank.winningCounter);
        if (lottoRank.bonusChecker) {
            rankInfo += STATISTICS_BONUS_BALL_FORMAT;
        }
        return rankInfo + String.format(STATISTICS_PRICE_FORMAT, lottoRank.prize);
    }

    public void displayProfit(double profit) {
        System.out.printf(PROFIT_FORMAT, profit);
        if (profit < PROFIT_DAMAGE_LIMIT) {
            System.out.println(PROFIT_DAMAGE_MESSAGE);
        }
    }
}
