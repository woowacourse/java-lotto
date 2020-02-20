package lotto.view;

import lotto.view.dto.StatisticsDTO;

public class OutputView {
    private static final String MESSAGE_FOR_BONUS_CASE = "%d개 일치, 보너스 볼 일치(%d원) - %d개";
    private static final String MESSAGE_FOR_DEFAULT_CASE = "%d개 일치 (%d원)- %d개";
    private static final String SECOND = "SECOND";

    public static void printResult(StatisticsDTO statisticsDTO) {
        System.out.println("당첨 통계");
        System.out.println("---------");
        for (int i = 0; i < statisticsDTO.size(); i++) {
            int matchCount = statisticsDTO.getMatchCount(i);
            int defaultPrize = statisticsDTO.getDefaultPrize(i);
            int matchTicketCount = statisticsDTO.getMatchTicketCount(i);
            String message = String.format(findMessage(statisticsDTO.getName(i)), matchCount, defaultPrize, matchTicketCount);
            System.out.println(message);
        }
        System.out.println(String.format("총 수익률은 %f입니다.", statisticsDTO.getRate()));
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
