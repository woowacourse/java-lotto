package lotto.view;

import java.util.List;
import lotto.model.money.Money;
import lotto.model.result.LottoRank;
import lotto.model.result.LottoStatistics;
import lotto.model.ticket.LottoTicket;
import lotto.model.ticket.LottoTickets;
import lotto.model.ticket.number.LottoNumber;

public class OutputView {

    public static void outputTickets(LottoTickets lottoTickets) {
        System.out.printf("%d개를 구매했습니다.%n", lottoTickets.size());
        List<LottoTicket> tickets = lottoTickets.getTickets();
        for (LottoTicket ticket : tickets) {
            outputTicket(ticket);
        }
        System.out.println();
    }

    private static void outputTicket(LottoTicket lottoTicket) {
        List<LottoNumber> numbers = lottoTicket.getNumbers();
        System.out.println(numbers);
    }

    public static void outputStatistics(LottoStatistics lottoStatistics, Money money) {
        outputConsoleFormat();
        outputStatisticsResult(lottoStatistics, money);
    }

    private static void outputConsoleFormat() {
        System.out.println("당첨 통계");
        System.out.println("---------");
    }

    private static void outputStatisticsResult(LottoStatistics lottoStatistics, Money money) {
        for (LottoRank value : LottoRank.values()) {
            System.out.printf("%d개 일치 (%d원)- %d개%n", value.getMatchCount(), value.getPrize(), lottoStatistics.count(value));
        }
        System.out.printf("총 수익률은 %.2f입니다.", lottoStatistics.calculateEarningRates(money));
    }
}
