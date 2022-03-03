package view;

import domain.RankPrize;
import domain.Result;
import dto.LottoDto;
import java.util.List;
import java.util.Map.Entry;

public class OutputView {

    private static final String ERROR_MESSAGE = "[ERROR] ";
    private static final String PURCHASE_MESSAGE = "수동으로 %d장, 자동으로 %d개를 구매했습니다.";
    private static final String WIN_STATISTICS_RESULT_MESSAGE = "당첨 통계\n---------";
    private static final String LOTTO_STATISTICS_EMPTY = "";
    private static final String SECOND_RANK_CORRECT_MESSAGE = "개 일치, 보너스 볼 일치(";
    private static final String RANK_PRICE_MESSAGE = "원)- ";
    private static final String RANK_COUNT_MESSAGE = "개";
    private static final String RANK_CORRECT_MESSAGE = "개 일치 (";

    public void printLotto(final List<LottoDto> issuedLotto, final int manualCount) {
        System.out.println();
        System.out.printf(OutputView.PURCHASE_MESSAGE, manualCount, issuedLotto.size() - manualCount);
        System.out.println();
        for (LottoDto lotto : issuedLotto) {
            System.out.println(lotto.get());
        }
    }

    public void printWinStatistics(final Result result) {
        System.out.println();
        System.out.println(WIN_STATISTICS_RESULT_MESSAGE);
        final StringBuilder stringBuilder = new StringBuilder();
        for (Entry<RankPrize, Integer> rankCount : result.getResult().entrySet()) {
            stringBuilder.append(getStatisticsWith(rankCount));
        }
        System.out.println(stringBuilder);
    }

    private String getStatisticsWith(final Entry<RankPrize, Integer> rankCount) {
        final RankPrize rankPrize = rankCount.getKey();
        if (rankPrize == RankPrize.NONE) {
            return LOTTO_STATISTICS_EMPTY;
        }
        if (rankPrize == RankPrize.SECOND) {
            return rankPrize.getCount() + SECOND_RANK_CORRECT_MESSAGE + rankPrize.getPrice() + RANK_PRICE_MESSAGE
                + rankCount.getValue() + RANK_COUNT_MESSAGE + System.lineSeparator();
        }
        return rankPrize.getCount() + RANK_CORRECT_MESSAGE + rankPrize.getPrice() + RANK_PRICE_MESSAGE
            + rankCount.getValue() + RANK_COUNT_MESSAGE + System.lineSeparator();
    }

    public void printWinProfit(final String profitOrNotMessage) {
        System.out.println(profitOrNotMessage);
    }

    public void printInputError(final Exception e) {
        System.out.println(ERROR_MESSAGE + e.getMessage());
    }
}
