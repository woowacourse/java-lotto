package lotto.view;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lotto.domain.LottoNumber;
import lotto.domain.LottoNumbers;
import lotto.domain.LottoTicket;
import lotto.domain.Ranking;
import lotto.domain.WinningResult;

public class OutputView {
    public static final String DELIMITER = ", ";

    public static final String TICKET_PURCHASE_SENTENCE_FORMAT = "%d개를 구매했습니다.%n";
    public static final String JOIN_LIST_FORMAT = "[%s]";
    public static final String CORRECT_COUNT_FORMAT = "%d개 일치";
    public static final String CORRECT_BONUS_BALL = ", 보너스 볼 일치";
    public static final String WINNING_PRICE_AND_WINNING_COUNT_FORMAT = "(%d원)- %d개%n";
    public static final String WINNING_RESULT_TITLE = "당첨 통계";
    public static final String WINNING_RESULT_SEPERATOR = "---------";
    public static final String PROFIT_SENTENCE_FORMAT = "총 수익률은 %.2f입니다.%n";

    private OutputView() {
    }

    public static void printTicketCount(int ticketCount) {
        System.out.printf(TICKET_PURCHASE_SENTENCE_FORMAT, ticketCount);
    }

    public static void printTicket(LottoTicket lottoTicket) {
        for (LottoNumbers lottoNumbers : lottoTicket.getLottoTickets()) {
            List<LottoNumber> lottoNumberList = lottoNumbers.getLottoNumbers();
            String sentence = joinList(convertToStringList(lottoNumberList));
            System.out.println(sentence);
        }
    }

    private static String joinList(List<String> list) {
        String str = String.join(DELIMITER, list);
        return String.format(JOIN_LIST_FORMAT, str);
    }

    private static List<String> convertToStringList(List<LottoNumber> lottoNumberList) {
        return lottoNumberList.stream()
                .sorted(Comparator.comparingInt(LottoNumber::getValue))
                .map(lottoNumber -> Integer.toString(lottoNumber.getValue()))
                .collect(Collectors.toList());
    }

    public static void printWinningStatistic(WinningResult winningResult) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<Ranking, Integer> entry : getWinningResultEntryList(winningResult)) {
            Ranking ranking = entry.getKey();
            int count = entry.getValue();
            generateResultContent(ranking, count, stringBuilder);
        }

        System.out.println(stringBuilder);
    }

    private static List<Map.Entry<Ranking, Integer>> getWinningResultEntryList(WinningResult winningResult) {
        return winningResult.getWinningResult().entrySet()
                .stream()
                .filter(entry -> entry.getKey() != Ranking.NONE)
                .sorted(Comparator.comparingInt(a -> a.getKey().getPrize()))
                .collect(Collectors.toList());
    }

    private static void generateResultContent(Ranking ranking, int count, StringBuilder stringBuilder) {
        String countSentence = String.format(CORRECT_COUNT_FORMAT, ranking.getCount());
        stringBuilder.append(countSentence);

        if (ranking.getHasBonusBall()) {
            stringBuilder.append(CORRECT_BONUS_BALL);
        }

        String str = String.format(WINNING_PRICE_AND_WINNING_COUNT_FORMAT, ranking.getPrize(), count);
        stringBuilder.append(str);
    }

    public static void printResultIntro() {
        System.out.println(WINNING_RESULT_TITLE);
        System.out.println(WINNING_RESULT_SEPERATOR);
    }

    public static void printProfit(double profit) {
        System.out.printf(PROFIT_SENTENCE_FORMAT, profit);
    }

    public static void printException(Exception exception) {
        System.out.println(exception.getMessage());
    }
}
