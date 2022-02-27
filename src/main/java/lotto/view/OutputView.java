package lotto.view;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import lotto.domain.LottoNumber;
import lotto.domain.LottoTickets;
import lotto.domain.Ranking;
import lotto.domain.WinningResult;
import lotto.domain.lottoticket.LottoTicket;

public class OutputView {
    private static final String OPEN_BRACKET = "[";
    private static final String CLOSE_BRACKET = "]";
    private static final String BLANK = " ";
    private static final String DELIMITER = ",";
    private static final String TICKET_PURCHASE_SENTENCE = "개를 구매했습니다.";
    private static final String NOT_INSTANTIATION_ERROR = "OutputView 객체를 생성 할 수 없습니다.";
    private static final String RESULT_PREFIX = "당첨 통계";
    private static final String RESULT_SEPARATION_LINE = "---------";
    private static final String LOTTO_TICKET_NUMBER_FORMAT = "%s%s%s";
    private static final String HIT_COUNT_FORMAT = "%d개 일치";
    private static final String SECOND_RANKING_SUFFIX = "보너스 볼 일치";
    private static final String PRIZE_AND_HIT_COUNT_FORMAT = "(%d원)- %d개%n";
    private static final String TOTAL_PROFIT_FORMAT = "총 수익률은 %.2f 입니다.";

    private OutputView() throws InstantiationException {
        throw new InstantiationException(NOT_INSTANTIATION_ERROR);
    }

    public static void printTicketCount(int ticketCount) {
        System.out.println(ticketCount + TICKET_PURCHASE_SENTENCE);
    }

    public static void printTicket(LottoTickets lottoTickets) {
        for (LottoTicket lottoTicket : lottoTickets.getValue()) {
            List<LottoNumber> lottoNumbers = lottoTicket.getValue();
            String sentence = joinList(convertToStringList(lottoNumbers));
            System.out.println(sentence);
        }
    }

    private static List<String> convertToStringList(List<LottoNumber> lottoNumbers) {
        return lottoNumbers.stream()
                .sorted(Comparator.comparingInt(LottoNumber::getValue))
                .map(lottoNumber -> Integer.toString(lottoNumber.getValue()))
                .collect(Collectors.toList());
    }

    private static String joinList(List<String> parsedList) {
        String joinedString = String.join(DELIMITER + BLANK, parsedList);
        return String.format(LOTTO_TICKET_NUMBER_FORMAT, OPEN_BRACKET, joinedString, CLOSE_BRACKET);
    }

    public static void printWinningStatistic(WinningResult winningResult) {
        printResultIntro();

        final StringBuilder stringBuilder = new StringBuilder();

        final Ranking[] sortedRankings = sortDescendingByPrize();

        for (Ranking ranking : sortedRankings) {
            int count = winningResult.getValue().get(ranking);

            addResultContent(ranking, count, stringBuilder);
        }

        System.out.println(stringBuilder);
    }

    private static Ranking[] sortDescendingByPrize() {
        final Ranking[] rankings = Ranking.values();
        Arrays.sort(rankings, Comparator.comparingInt(Ranking::getPrize));
        return rankings;
    }

    private static void printResultIntro() {
        System.out.println(RESULT_PREFIX);
        System.out.println(RESULT_SEPARATION_LINE);
    }

    private static void addResultContent(Ranking ranking, int count, StringBuilder stringBuilder) {
        String countSentence = String.format(HIT_COUNT_FORMAT, ranking.getHitCount());
        stringBuilder.append(countSentence);

        if (ranking.isSecond()) {
            stringBuilder.append(DELIMITER);
            stringBuilder.append(BLANK);
            stringBuilder.append(SECOND_RANKING_SUFFIX);
        }

        String prizeAndCount = String.format(PRIZE_AND_HIT_COUNT_FORMAT, ranking.getPrize(), count);
        stringBuilder.append(prizeAndCount);
    }

    public static void printProfit(double profit) {
        String profitSentence = String.format(TOTAL_PROFIT_FORMAT, profit);
        System.out.println(profitSentence);
    }

    public static void printException(Exception exception) {
        System.out.println(exception.getMessage());
    }
}
