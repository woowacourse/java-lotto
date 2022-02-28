package view;

import domain.RankPrize;
import dto.LottoDto;
import java.util.List;
import java.util.Map.Entry;
import java.util.SortedMap;

public class OutputView {

    private static final String PURCHASE_MESSAGE = "개를 구매했습니다.";
    private static final String WIN_STATISTICS_RESULT_MESSAGE = "당첨 통계\n---------";
    private static final String SECOND_RANK_CORRECT_MESSAGE = "개 일치, 보너스 볼 일치(";
    private static final String RANK_PRICE_MESSAGE = "원)- ";
    private static final String RANK_COUNT_MESSAGE = "개";
    private static final String RANK_CORRECT_MESSAGE = "개 일치 (";
    private static final String WIN_PROFIT_RESULT_MESSAGE = "총 수익률은 %.2f입니다. (기준이 1 이기 때문에 결과적으로 %s라는 의미임)";


    public void printLotto(final List<LottoDto> issuedLotto) {
        System.out.println(issuedLotto.size() + PURCHASE_MESSAGE);
        for (LottoDto lotto : issuedLotto) {
            System.out.println(lotto.get());
        }
    }


    public void printWinStatistics(final SortedMap<RankPrize, Integer> result) {
        System.out.println();
        System.out.println(WIN_STATISTICS_RESULT_MESSAGE);
        for (Entry<RankPrize, Integer> rankCount : result.entrySet()) {
            printWinStatistics(rankCount);
        }
    }

    private void printWinStatistics(final Entry<RankPrize, Integer> rankCount) {
        final RankPrize rankPrize = rankCount.getKey();
        if (rankPrize == RankPrize.NONE) {
            return;
        }
        if (rankPrize == RankPrize.SECOND) {
            System.out.println(
                (rankPrize.getCount()) + SECOND_RANK_CORRECT_MESSAGE + rankPrize.getPrice()
                    + RANK_PRICE_MESSAGE
                    + rankCount.getValue()
                    + RANK_COUNT_MESSAGE);
            return;
        }
        System.out.println(
            rankPrize.getCount() + RANK_CORRECT_MESSAGE + rankPrize.getPrice() + RANK_PRICE_MESSAGE
                + rankCount.getValue() + RANK_COUNT_MESSAGE);
    }

    public void printWinProfit(final double calculateProfit) {
        String profitResultMessage = "손해";
        if (calculateProfit >= 1) {
            profitResultMessage = "이익";
        }
        System.out.printf((WIN_PROFIT_RESULT_MESSAGE) + "%n", calculateProfit, profitResultMessage);
    }
}
