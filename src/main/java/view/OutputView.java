package view;

import domain.LottoProfit;
import domain.LottoRank;
import domain.LottoResult;
import domain.numberscontainer.Tickets;

public class OutputView {
    private static final String MANUAL_AND_RANDOM_TICKET_SIZE_MESSAGE = "수동으로 %d장, 자동으로 %d장을 구매했습니다.";
    private static final String LOTTO_RESULT_MESSAGE = "%d개 일치 (%d원)- %d개";
    private static final String LOTTO_RESULT_MESSAGE_FOR_BONUS = "%d개 일치, 보너스 볼 일치 (%d원)- %d개";
    private static final String LOTTO_RESULT_TITLE = "당첨 통계";
    private static final String LOTTO_RESULT_SEPARATOR = "---------------";
    private static final String LOTTO_PROFIT_MESSAGE = "총 수익률은 %d%%입니다.";

    public static void printNumberOfTickets(int manualTicketSize, int randomTicketSize) {
        System.out.println(String.format(MANUAL_AND_RANDOM_TICKET_SIZE_MESSAGE, manualTicketSize, randomTicketSize));
    }

    public static void printTickets(Tickets tickets) {
        System.out.println(tickets);
    }

    public static void printLottoResults(LottoResult lottoResults) {
        System.out.println(LOTTO_RESULT_TITLE + System.lineSeparator() + LOTTO_RESULT_SEPARATOR);

        for (LottoRank rank : LottoRank.values()) {
            System.out.println(String.format(findProperFormat(rank), rank.getMatchCount(), rank.getPrize(), convertNullToZero(lottoResults.count(rank))));
        }
    }

    private static String findProperFormat(LottoRank result) {
        if (result == LottoRank.SECOND) {
            return LOTTO_RESULT_MESSAGE_FOR_BONUS;
        }
        return LOTTO_RESULT_MESSAGE;
    }

    private static int convertNullToZero(Integer number) {
        if (number == null) {
            return 0;
        }
        return number;
    }

    public static void printProfit(LottoProfit profit) {
        System.out.println(String.format(LOTTO_PROFIT_MESSAGE, profit.getValue()));
    }
}