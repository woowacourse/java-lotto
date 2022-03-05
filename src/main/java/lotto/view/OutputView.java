package lotto.view;

import java.util.List;

import java.util.Map;
import lotto.domain.LottoRank;
import lotto.dto.LottoTicketResponse;
import lotto.dto.StatisticsResult;

public class OutputView {

    public static final String NEW_LINE = System.getProperty("line.separator");
    public static final String LOTTO_COUNT_FORMAT = NEW_LINE + "수동으로 %d장, 자동으로 %d개를 구매했습니다." + NEW_LINE;

    public void outputLottoCount(final int manualCount, final int randomCount) {
        System.out.printf(LOTTO_COUNT_FORMAT, manualCount, randomCount);
    }

    public void outputTickets(final List<LottoTicketResponse> responses) {
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
        System.out.print(NEW_LINE + "당첨 통계" + NEW_LINE + "---------");
    }

    public void displayStatisticsByRank(final LottoRank rank, final long count) {
        if (rank == LottoRank.SIXTH) {
            return;
        }
        System.out.printf(findMessage(rank), rank.getMatchCount(), rank.getPrize(), count);
    }

    private String findMessage(final LottoRank rank) {
        if (rank == LottoRank.SECOND) {
            return NEW_LINE + "%d개 일치, 보너스 볼 일치 (%d)원- %d개";
        }
        return NEW_LINE + "%d개 일치 (%d원)- %d개";
    }

    public void outputEarningRate(final String earningRate) {
        System.out.printf("총 수익률은 %s입니다.", earningRate);
    }
}
