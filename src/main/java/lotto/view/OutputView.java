package lotto.view;

import java.util.List;
import lotto.model.money.Money;
import lotto.model.result.LottoRank;
import lotto.model.result.LottoStatistics;
import lotto.model.ticket.LottoTicket;
import lotto.model.ticket.LottoTickets;
import lotto.model.ticket.buy.ManualBuyCount;
import lotto.model.ticket.number.LottoNumber;

public class OutputView {

    public static final String BUY_COUNT_MESSAGE = "수동으로 %d장, 자동으로 %d 개를 구매했습니다.\n";
    public static final String MATCH_COUNT_MESSAGE = "%d개 일치 (%d원)- %d개%n";
    public static final String EARNING_RATE_MESSAGE = "총 수익률은 %.2f입니다.";
    public static final String OUTPUT_TITLE = "당첨 통계";
    public static final String OUTPUT_LINE = "---------";

    //인스턴스화 방지
    private OutputView() {
    }

    public static void outputTickets(LottoTickets lottoTickets) {
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
        System.out.println(OUTPUT_TITLE);
        System.out.println(OUTPUT_LINE);
    }

    private static void outputStatisticsResult(LottoStatistics lottoStatistics, Money money) {
        for (LottoRank value : LottoRank.values()) {
            System.out.printf(MATCH_COUNT_MESSAGE, value.getMatchCount(), value.getPrize(), lottoStatistics.count(value));
        }
        System.out.printf(EARNING_RATE_MESSAGE, lottoStatistics.calculateEarningRates(money));
    }
}
