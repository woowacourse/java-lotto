package lotto.view;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import lotto.domain.LottoResult;
import lotto.domain.LottoTickets;
import lotto.domain.Prize;

public class OutputView {
    public void printTicketsSize(int size) {
        System.out.printf("%d개를 구매했습니다.\n", size);
    }

    public void printAllLottoTickets(LottoTickets lottoTickets) {
        lottoTickets.list().forEach(lottoTicket ->
            System.out.println(lottoTicket.printLottoTicket())
        );
    }

    public void printLottoResult(LottoResult lottoResult, int purchaseMoney) {
        System.out.println("당첨 통계");
        System.out.println("---------");
        printWinningResult(lottoResult.getLottoResult());
        printProfitRatio(purchaseMoney, lottoResult.calculatePrizeMoney());
    }

    public void printWinningResult(Map<Prize, Long> resultMap) {
        Arrays.stream(Prize.values())
            .filter(prize -> prize != Prize.LOSING)
            .sorted(Comparator.comparing(Prize::getRank))
            .sorted(Comparator.reverseOrder())
            .forEach(prize ->
                System.out.println(makeWinningResultMessage(prize, resultMap))
            );
    }

    public String makeWinningResultMessage(Prize prize, Map<Prize, Long> resultMap) {
        Long winningCount = resultMap.get(prize);
        if (winningCount == null) {
            winningCount = 0L;
        }
        return String.format(findFormat(prize), prize.getMatchCount(), prize.getPrizeMoney(),
            winningCount);
    }

    public String findFormat(Prize prize) {
        if (Prize.SECOND == prize) {
            return "%d개 일치, 보너스 볼 일치(%d원) - %d개";
        }
        return "%d개 일치(%d원) - %d개";
    }

    public void printProfitRatio(int purchaseMoney, Long prizeMoney) {
        System.out.printf("총 수익률은 %d%%입니다.", (prizeMoney * 100) / purchaseMoney);
    }
}
