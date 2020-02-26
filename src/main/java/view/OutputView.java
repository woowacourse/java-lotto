package view;

import domain.LottoProfit;
import domain.LottoResult;
import domain.numberscontainer.Ticket;

import java.util.List;
import java.util.Map;

public class OutputView {
    public static final String AUTO_AND_MANUAL_TICKET_SIZE = "수동으로 %d장, 자동으로 %d개를 구매했습니다.";
    public static final String LOTTO_RESULT_MESSAGE = "%d개 일치 (%d원)- %d개";
    public static final String LOTTO_RESULT_MESSAGE_FOR_BONUS = "%d개 일치, 보너스 볼 일치 (%d원)- %d개";
    public static final String LOTTO_RESULT_TITLE = "당첨 통계";
    public static final String LOTTO_RESULT_SEPARATOR = "---------------";
    public static final String LOTTO_PROFIT_MESSAGE = "총 수익률은 %d%%입니다.";

    public static void printNumberOfTickets(int manualTicketSize, int autoTicketSize) {
        System.out.println(String.format(AUTO_AND_MANUAL_TICKET_SIZE, manualTicketSize, autoTicketSize));
    }

    public static void printTickets(List<Ticket> tickets) {
        for (Ticket ticket : tickets) {
            System.out.println(ticket.toString());
        }
    }

    public static void printLottoResults(Map<LottoResult, Integer> lottoResults) {
        System.out.println(LOTTO_RESULT_TITLE + System.lineSeparator() + LOTTO_RESULT_SEPARATOR);

        for (LottoResult result : LottoResult.values()) {
            System.out.println(String.format(findProperFormat(result), result.getMatchCount(), result.getPrize(), convertNullToZero(lottoResults.get(result))));
        }
    }

    private static String findProperFormat(LottoResult result) {
        if (result == LottoResult.SECOND) {
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