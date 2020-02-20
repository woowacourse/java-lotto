package lotto.view;

import lotto.view.dto.StatisticsResponseDTO;

public class OutputView {
    private static final String MESSAGE_FOR_BONUS_CASE = "%d개 일치, 보너스 볼 일치(%d원) - %d개";
    private static final String MESSAGE_FOR_DEFAULT_CASE = "%d개 일치 (%d원)- %d개";
    private static final String SECOND = "SECOND";
    private static final String RATE_MESSAGE = "총 수익률은 %s입니다.";
    private static final String RESULT_HEADER = "당첨 통계\n-----------";

    public static void printResult(StatisticsResponseDTO statisticsResponseDTO) {
        System.out.println(RESULT_HEADER);
        printStatistics(statisticsResponseDTO);
        System.out.println(String.format(RATE_MESSAGE, statisticsResponseDTO.getRate()));
    }

    private static void printStatistics(StatisticsResponseDTO statisticsResponseDTO) {
        for (int i = 0; i < statisticsResponseDTO.size(); i++) {
            int matchCount = statisticsResponseDTO.getMatchCount(i);
            int defaultPrize = statisticsResponseDTO.getDefaultPrize(i);
            int matchTicketCount = statisticsResponseDTO.getMatchTicketCount(i);
            String message = String.format(findMessage(statisticsResponseDTO.getName(i)), matchCount, defaultPrize, matchTicketCount);
            System.out.println(message);
        }
    }

    private static String findMessage(String name) {
        if (isSecond(name)) {
            return MESSAGE_FOR_BONUS_CASE;
        }
        return MESSAGE_FOR_DEFAULT_CASE;
    }

    private static boolean isSecond(String name) {
        return SECOND.equals(name);
    }

}
