package lotto.view;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import lotto.domain.LottoResult;
import lotto.domain.Money;
import lotto.domain.Prize;
import lotto.domain.ticket.LottoTickets;

public class OutputView {
    public void printAllLottoTickets(LottoTickets lottoTickets) {
        System.out.printf("%d개를 구매했습니다.\n", lottoTickets.getLottoTickets().size());
        lottoTickets.getLottoTickets().forEach(lottoTicket ->
            System.out.println(Arrays.toString(lottoTicket.getLottoNumbers().toArray()))
        );
    }

    public void printLottoResult(LottoResult lottoResult, Money purchaseMoney) {
        System.out.println("당첨 통계");
        System.out.println("---------");
        printWinningResult(lottoResult.getLottoResult());
        printProfitRatio(purchaseMoney.getMoney(), lottoResult.getPrizeMoney());
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
        long winningCount = resultMap.getOrDefault(prize, 0L);
        return String
            .format(findFormat(prize), prize.getMatchCount(), prize.getPrizeMoney(), winningCount);
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
