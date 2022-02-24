package lotto.view;

import java.util.List;

import java.util.Map;
import lotto.domain.LottoRank;
import lotto.dto.LottoTicketResponse;
import lotto.dto.StatisticsResult;

public class OutputView {

    private static final String NEW_LINE = System.getProperty("line.separator");

    public void outputTickets(List<LottoTicketResponse> responses) {
        System.out.printf("%d개를 구매했습니다." + NEW_LINE, responses.size());
        for (LottoTicketResponse response : responses) {
            outputTicket(response);
        }
        System.out.println();
    }

    private void outputTicket(LottoTicketResponse lottoTicketResponse) {
        System.out.println(lottoTicketResponse.getLottoTicketResponse());
    }

    public void outputStatisticsResult(StatisticsResult statisticsResult) {
        displayStatisticsPrompt();
        Map<LottoRank, Long> result = statisticsResult.getStatisticsByRank();
        result.forEach(this::displayStatisticsByRank);
        System.out.println();
    }

    public void displayStatisticsPrompt() {
        System.out.println(NEW_LINE + "당첨 통계" + NEW_LINE + "---------");
    }

    public void displayStatisticsByRank(final LottoRank rank, final long count) {
        if (rank == LottoRank.SIXTH) {
            return;
        }
        System.out.printf(findMessage(rank), rank.getMatchCount(), rank.getPrize(), count);
    }

    private String findMessage(final LottoRank rank) {
        if (rank == LottoRank.SECOND) {
            return "%d개 일치, 보너스 볼 일치 (%d)원- %d개" + NEW_LINE;
        }
        return "%d개 일치 (%d원)- %d개" + NEW_LINE;
    }

    public void outputEarningRate(double earningRate) {
        System.out.printf("총 수익률은 %.2f입니다.", earningRate);
    }
}
