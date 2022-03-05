package lotto.view;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import lotto.domain.LottoNumber;
import lotto.domain.Rank;
import lotto.domain.Ticket;
import lotto.domain.Tickets;

public class OutputView {
    private static final String LOSS_MESSAGE = "(기준이 1이기 때문에 결과적으로 손해라는 의미임)";
    private static final String DELIMITER = ", ";
    private static final String RESULT_HEADER_MESSAGE = "당첨 통계";
    private static final String DIVIDER = "---------";
    private static final int PROFIT_BASIS = 1;

    public static void printTickets(int manualTicketCount, int autoTicketCount, Tickets tickets) {
        StringBuilder stringBuilder = new StringBuilder();
        appendPrintTicketHeaderToStringBuilder(manualTicketCount, autoTicketCount, stringBuilder);
        appendTicketsToStringBuilder(stringBuilder, tickets);
        System.out.println(stringBuilder);
    }

    private static void appendPrintTicketHeaderToStringBuilder(int manualTicketCount, int autoTicketCount,
        StringBuilder stringBuilder) {
        stringBuilder.append("수동으로 ")
            .append(manualTicketCount)
            .append("장, 자동으로 ")
            .append(autoTicketCount)
            .append("개를 구매했습니다.").append("\n");
    }

    private static void appendTicketsToStringBuilder(StringBuilder stringBuilder, Tickets tickets) {
        for (Ticket ticket : tickets.getTickets()) {
            Set<LottoNumber> lottoNumbers = ticket.getLottoNumbers();
            stringBuilder.append("[")
                .append(String.join(DELIMITER, getLottoNumbers(lottoNumbers)))
                .append("]\n");
        }
    }

    private static List<String> getLottoNumbers(Set<LottoNumber> lottoNumbers) {
        return lottoNumbers.stream()
            .map(LottoNumber::getNumber)
            .map(String::valueOf)
            .collect(Collectors.toList());
    }

    public static void printResult(Map<Rank, Integer> result, double yield) {
        StringBuilder stringBuilder = new StringBuilder();
        appendResultHeaderToStringBuilder(stringBuilder);
        appendResultToStringBuilder(stringBuilder, result);
        System.out.print(stringBuilder);
        printYield(yield);
    }

    private static void appendResultHeaderToStringBuilder(StringBuilder stringBuilder) {
        stringBuilder.append(RESULT_HEADER_MESSAGE)
            .append("\n")
            .append(DIVIDER)
            .append("\n");
    }

    private static void appendResultToStringBuilder(StringBuilder stringBuilder, Map<Rank, Integer> result) {
        for (Rank rank : Rank.getRanks()) {
            stringBuilder.append(rank.getCount())
                .append("개 일치")
                .append(getBonus(rank));
            stringBuilder.append("(")
                .append(rank.getAmount()).append("원) - ")
                .append(getCount(result, rank))
                .append("개\n");
        }
    }

    private static String getBonus(Rank rank) {
        if (rank.isBonus()) {
            return ", 보너스 볼 일치";
        }
        return " ";
    }

    private static int getCount(Map<Rank, Integer> result, Rank rank) {
        if (result.containsKey(rank)) {
            return result.get(rank);
        }
        return 0;
    }

    private static void printYield(double yield) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("총 수익률은 ")
                .append(yield)
                .append("입니다.");
        if (yield < PROFIT_BASIS) {
            stringBuilder.append(LOSS_MESSAGE);
        }
        System.out.println(stringBuilder);
    }

    public static void printErrorMessage(String message) {
        System.out.println(message);
    }
}
