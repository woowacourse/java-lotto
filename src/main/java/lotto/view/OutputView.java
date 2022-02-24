package lotto.view;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lotto.domain.LottoNumber;
import lotto.domain.LottoNumbers;
import lotto.domain.LottoTickets;
import lotto.domain.Ranking;
import lotto.domain.WinningResult;

public class OutputView {
    public static final String OPEN_BRACKET = "[";
    public static final String CLOSE_BRACKET = "]";
    public static final String BLANK = " ";
    public static final String DELIMITER = ",";
    public static final String TICKET_PURCHASE_SENTENCE = "개를 구매했습니다.";
    public static final String LINE_SEPARATOR = System.lineSeparator();

    private OutputView() {
    }

    public static void printTicketCount(int ticketCount) {
        System.out.println(ticketCount + TICKET_PURCHASE_SENTENCE);
    }

    public static void printTicket(LottoTickets lottoTickets) {
        List<LottoNumbers> lottoNumbersList = lottoTickets.getLottoTickets();

        for (LottoNumbers lottoNumbers : lottoNumbersList) {
            List<LottoNumber> lottoNumberList = lottoNumbers.getLottoNumbers();
            String sentence = joinList(convertLottoNumberListToIntegerList(lottoNumberList));
            System.out.println(sentence);
        }
    }

    private static String joinList(List<String> list) {
        String str = String.join(DELIMITER + BLANK, list);
        return String.format("%s%s%s", OPEN_BRACKET, str, CLOSE_BRACKET);
    }

    private static List<String> convertLottoNumberListToIntegerList(List<LottoNumber> lottoNumberList) {
        return lottoNumberList.stream()
                .sorted(Comparator.comparingInt(LottoNumber::toInt))
                .map(lottoNumber -> Integer.toString(lottoNumber.toInt()))
                .collect(Collectors.toList());
    }

    public static void printWinningStatistic(WinningResult winningResult) {
        Map<Ranking, Integer> winningResultMap = winningResult.getWinningResult();
        StringBuilder stringBuilder = new StringBuilder();

        resultIntro(stringBuilder);

        for (Map.Entry<Ranking, Integer> entry : winningResultMap.entrySet()) {
            Ranking ranking = entry.getKey();
            int count = entry.getValue();
            generateResultContent(ranking, count, stringBuilder);
        }
        System.out.println(stringBuilder);
    }

    private static void generateResultContent(Ranking ranking, int count, StringBuilder stringBuilder) {
        String countSentence = String.format("%d개 일치", ranking.getCount());
        stringBuilder.append(countSentence);

        if (ranking.getHasBonusBall()) {
            stringBuilder.append(", 보너스 볼 일치");
        }

        String str = String.format("(%d원)- %d개%s", ranking.getPrize(), count, LINE_SEPARATOR);
        stringBuilder.append(str);
    }

    private static void resultIntro(StringBuilder stringBuilder) {
        stringBuilder.append("당첨 통계")
                .append(LINE_SEPARATOR)
                .append("---------")
                .append(LINE_SEPARATOR);
    }

    public static void printProfit(double profit) {
        String sentence = String.format("총 수익률은 %.2f입니다.", profit);
        System.out.println(sentence);
    }
}
