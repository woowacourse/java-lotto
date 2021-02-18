package lotto.view;

import lotto.domain.LottoResult;
import lotto.domain.Prize;
import lotto.domain.ticket.LottoTicket;
import lotto.domain.ticket.LottoTickets;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;

public class OutputView {
    private static final String INFORM_SIZE_MSG = "%d개를 구매했습니다.\n";
    private static final String WINNING_STATISTICS = "당첨 통계";
    private static final String DASHES = "---------";
    private static final String INFORM_RESULT_AND_BONUS_MSG_FORMAT = "%d개 일치, 보너스 볼 일치(%d원) - %d개";
    private static final String INFORM_RESULT_MSG_FORMAT = "%d개 일치(%d원) - %d개";
    private static final String INFORM_PROFIT_RATIO_MSG_FORMAT = "총 수익률은 %d%%입니다.";
    public static final String COMMA = ",";
    public static final String TICKET_PREFIX = "[";
    public static final String TICKET_SUFFIX = "]";

    public void printTicketsSize(int size) {
        System.out.printf(INFORM_SIZE_MSG, size);
    }

    public void printAllLottoTickets(LottoTickets lottoTickets) {
        lottoTickets.list().forEach(lottoTicket ->
                System.out.println(makeEachLottoTicketToString(lottoTicket))
        );
    }

    private String makeEachLottoTicketToString(LottoTicket lottoTicket){
        return lottoTicket.list().stream()
                .map(Object::toString)
                .collect(Collectors.joining(COMMA, TICKET_PREFIX, TICKET_SUFFIX));
    }

    public void printLottoResult(LottoResult lottoResult, int purchaseMoney) {
        System.out.println(WINNING_STATISTICS);
        System.out.println(DASHES);
        printWinningResult(lottoResult);
        printProfitRatio(purchaseMoney, lottoResult.calculatePrizeMoney());
    }

    public void printWinningResult(LottoResult lottoResult) {
        Arrays.stream(Prize.values())
                .filter(prize -> prize != Prize.LOSING)
                .sorted(Comparator.comparing(Prize::getRank))
                .sorted(Comparator.reverseOrder())
                .forEach(prize ->
                        System.out.println(makeWinningResultMessage(prize, lottoResult))
                );
    }

    public String makeWinningResultMessage(Prize prize, LottoResult lottoResult) {
        Long winningCount = lottoResult.get(prize);
        if (winningCount == null) {
            winningCount = 0L;
        }
        return String.format(findFormat(prize), prize.getMatchCount(), prize.getPrizeMoney(),
                winningCount);
    }

    public String findFormat(Prize prize) {
        if (Prize.SECOND == prize) {
            return INFORM_RESULT_AND_BONUS_MSG_FORMAT;
        }
        return INFORM_RESULT_MSG_FORMAT;
    }

    public void printProfitRatio(int purchaseMoney, Long prizeMoney) {
        System.out.printf(INFORM_PROFIT_RATIO_MSG_FORMAT, (prizeMoney * 100) / purchaseMoney);
    }
}
