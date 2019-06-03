package lotto.view;

import lotto.domain.LottoResult;
import lotto.domain.lottoseller.LottoSeller;
import lotto.domain.lottoticket.LottoTickets;
import lotto.domain.Rank;

public class OutputView {
    private static final String LINE_SEPARATOR = System.getProperty("line.separator");
    private static final String OUTPUT_NUM_OF_PURCHASED_TICKETS_MESSAGE = "장을 구매했습니다.";
    private static final String OUTPUT_CHANGE_MESSAGE_FRONT = " 잔돈은 ";
    private static final String OUTPUT_CHANGE_MESSAGE_BACK = "원입니다.";
    private static final String OUTPUT_RESULT_STATISTICS_MESSAGE = "당첨 통계" + LINE_SEPARATOR + "----------";
    private static final String OUTPUT_RANK_RESULT_MESSAGE_1 = "개 일치 (";
    private static final String OUTPUT_RANK_RESULT_MESSAGE_2 = "원) - ";
    private static final String OUTPUT_RANK_RESULT_MESSAGE_3 = "개";

    public static void showNumOfTicketsFrom(LottoSeller lottoSeller) {
        System.out.println(makeNumOfTicketsMessage(lottoSeller));
    }

    private static String makeNumOfTicketsMessage(LottoSeller lottoSeller) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(lottoSeller.getNumOfLotto())
                .append(OUTPUT_NUM_OF_PURCHASED_TICKETS_MESSAGE);

        if (lottoSeller.hasChange()) {
            stringBuilder.append(OUTPUT_CHANGE_MESSAGE_FRONT)
                    .append(lottoSeller.getChange())
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
    }

    private static void showAllOf(LottoResult lottoResult) {
        for (Rank rank : Rank.valuesWithoutMISS()) {
            System.out.print(generateResultMessage(rank, lottoResult.getCountsBy(rank)));
        }
    }

    private static String generateResultMessage(Rank rank, int countOfRankResult) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(rank.getNumOfMatching())
                .append(OUTPUT_RANK_RESULT_MESSAGE_1)
                .append(rank.getPrize())
                .append(OUTPUT_RANK_RESULT_MESSAGE_2)
                .append(countOfRankResult)
                .append(OUTPUT_RANK_RESULT_MESSAGE_3)
                .append(LINE_SEPARATOR);
        return stringBuilder.toString();
    }
}
