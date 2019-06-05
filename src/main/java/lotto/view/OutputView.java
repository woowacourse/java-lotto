package lotto.view;

import lotto.domain.LottoResult;
import lotto.domain.Rank;
import lotto.domain.lottomoney.MoneyForLotto;
import lotto.domain.lottoticket.LottoTickets;

public class OutputView {
    private static final String LINE_SEPARATOR = System.getProperty("line.separator");
    private static final String OUTPUT_NUM_OF_PURCHASED_TICKETS_MESSAGE_1 = "수동으로 ";
    private static final String OUTPUT_NUM_OF_PURCHASED_TICKETS_MESSAGE_2 = "장, 자동으로 ";
    private static final String OUTPUT_CHANGE_MESSAGE_FRONT = " 잔돈은 ";
    private static final String OUTPUT_CHANGE_MESSAGE_BACK = "원입니다.";
    private static final String OUTPUT_RESULT_STATISTICS_MESSAGE = "당첨 통계" + LINE_SEPARATOR + "----------";
    private static final String OUTPUT_RANK_RESULT_MESSAGE_1 = "개 일치 (";
    private static final String OUTPUT_RANK_RESULT_BONUS_BALL_MESSAGE = "개 일치, 보너스 볼 일치 (";
    private static final String OUTPUT_RANK_RESULT_MESSAGE_2 = "원) - ";
    private static final String OUTPUT_RANK_RESULT_MESSAGE_3 = "개";
    private static final String OUTPUT_PROFIT_RATIO_MESSAGE_FRONT = "총 수익률은 ";
    private static final String OUTPUT_PROFIT_RATIO_MESSAGE_BACK = "%입니다.";
    public static final String OUTPUT_NUM_OF_PURCHASED_TICKETS_MESSAGE_3 = "장을 구매했습니다.";

    public static void showNumOfTicketsFrom(MoneyForLotto moneyForLotto, long numOfManualTickets) {
        System.out.println(makeNumOfPurchasedTicketsMessage(moneyForLotto, numOfManualTickets));
    }

    private static String makeNumOfPurchasedTicketsMessage(MoneyForLotto moneyForLotto, long numOfManualTickets) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(OUTPUT_NUM_OF_PURCHASED_TICKETS_MESSAGE_1)
                .append(numOfManualTickets)
                .append(OUTPUT_NUM_OF_PURCHASED_TICKETS_MESSAGE_2)
                .append(moneyForLotto.getNumOfLotto() - numOfManualTickets)
                .append(OUTPUT_NUM_OF_PURCHASED_TICKETS_MESSAGE_3);

        if (moneyForLotto.hasChange()) {
            stringBuilder.append(OUTPUT_CHANGE_MESSAGE_FRONT)
                    .append(moneyForLotto.getChange())
                    .append(OUTPUT_CHANGE_MESSAGE_BACK);
        }
        return stringBuilder.toString();
    }

    public static void showAllOf(LottoTickets lottoTickets) {
        for (int index = 0; index < lottoTickets.size(); index++) {
            System.out.println(lottoTickets.getTicket(index));
        }
        System.out.print(LINE_SEPARATOR);
    }

    public static void showStatisticsOf(LottoResult lottoResult) {
        System.out.print(LINE_SEPARATOR);
        System.out.println(OUTPUT_RESULT_STATISTICS_MESSAGE);
        showAllOf(lottoResult);
        System.out.println(OUTPUT_PROFIT_RATIO_MESSAGE_FRONT
                + convertToPercent(lottoResult.getProfitRatio())
                + OUTPUT_PROFIT_RATIO_MESSAGE_BACK);
    }

    private static void showAllOf(LottoResult lottoResult) {
        for (Rank rank : Rank.valuesWithoutMiss()) {
            System.out.print(generateResultMessage(rank, lottoResult.getCountsBy(rank)));
        }
    }

    private static String generateResultMessage(Rank rank, int countOfRankResult) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(rank.getNumOfMatching())
                .append(generateNumOfMatchingMessage(rank))
                .append(rank.getPrize())
                .append(OUTPUT_RANK_RESULT_MESSAGE_2)
                .append(countOfRankResult)
                .append(OUTPUT_RANK_RESULT_MESSAGE_3)
                .append(LINE_SEPARATOR);
        return stringBuilder.toString();
    }

    private static String generateNumOfMatchingMessage(Rank rank) {
        if (rank == Rank.SECOND) {
            return OUTPUT_RANK_RESULT_BONUS_BALL_MESSAGE;
        }
        return OUTPUT_RANK_RESULT_MESSAGE_1;
    }

    private static String convertToPercent(double ratio) {
        return String.format("%.2f", (ratio * 100));
    }
}
