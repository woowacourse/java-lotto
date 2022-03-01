package lotto.view;

import java.util.Map;
import lotto.dto.LottoResultDto;
import lotto.dto.LottoTicketDto;
import lotto.dto.LottoTicketsDto;
import lotto.dto.RankDto;

public class OutputView {

    private static final String LOTTO_COUNT_FORMAT = "개를 구매했습니다.";
    private static final String STATISTICS_GUIDE_MESSAGE = "당첨 통계\n---------";
    private static final String YIELD_FORMAT = "총 수익률은 %.2f입니다.";
    private static final String STATISTICS_FORMAT = " (%d원) - %d개";
    private static final String YIELD_GAIN_MESSAGE = "(기준이 1이기 때문에 결과적으로 이득이라는 의미임)";
    private static final String YIELD_LOSS_MESSAGE = "(기준이 1이기 때문에 결과적으로 손해라는 의미임)";
    private static final int YIELD_STANDARD = 1;

    public static void displayLottoTickets(LottoTicketsDto lottoTicketsDto) {
        System.out.println(lottoTicketsDto.getLottoTickets().size() + LOTTO_COUNT_FORMAT);
        for (LottoTicketDto lottoTicketDto : lottoTicketsDto.getLottoTickets()) {
            System.out.println(lottoTicketDto.getNumbers().toString());
        }
        System.out.println();
    }

    public static void displayLottoResult(LottoResultDto lottoResultDto) {
        displayStatistics(lottoResultDto.getStatistics());
        displayYield(lottoResultDto.getYield());
    }

    private static void displayStatistics(Map<RankDto, Integer> statistics) {
        System.out.println();
        System.out.println(STATISTICS_GUIDE_MESSAGE);
        statistics.forEach(OutputView::displayRankResult);
    }

    private static void displayYield(double calculateYield) {
        System.out.println(String.format(YIELD_FORMAT, calculateYield) + isLoss(calculateYield));
    }

    private static void displayRankResult(RankDto rankDto, Integer count) {
        System.out.println(
                rankDto.getMatchStatus() + String.format(STATISTICS_FORMAT, rankDto.getReward(), count));
    }

    private static String isLoss(double calculateYield) {
        if (calculateYield >= YIELD_STANDARD) {
            return YIELD_GAIN_MESSAGE;
        }
        return YIELD_LOSS_MESSAGE;
    }
}