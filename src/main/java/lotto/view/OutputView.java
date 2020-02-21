package lotto.view;

import lotto.domain.result.LottoResultBundle;
import lotto.domain.result.win.rank.Rank;
import lotto.domain.ticket.LottoTicket;
import lotto.domain.ticket.LottoTicketBundle;
import lotto.domain.ticket.ball.LottoBall;

public class OutputView {
    private static final String MESSAGE_FOR_BONUS_CASE = "%d개 일치, 보너스 볼 일치(%d원) - %d개";
    private static final String MESSAGE_FOR_DEFAULT_CASE = "%d개 일치 (%d원)- %d개";
    private static final String SECOND = "SECOND";
    private static final String RATE_MESSAGE = "총 수익률은 %s입니다.";
    private static final String RESULT_HEADER = "당첨 통계\n-----------";
    private static final String LOTTO_NUMBERS_FORMAT = "[ %d %d %d %d %d %d ]";
    private static final String BUY_LOTTO_TICKET_COUNT_MESSAGE = "%d개를 구매하였습니다.";

    public static void printBuyTicketCount(int count) {
        System.out.println(String.format(BUY_LOTTO_TICKET_COUNT_MESSAGE, count));
    }

    public static void printBuyTickets(LottoTicketBundle lottoTicketBundle) {
        for (LottoTicket lottoTicket : lottoTicketBundle.getLottoTickets()) {
            System.out.println(String.format(LOTTO_NUMBERS_FORMAT, makeLottoNumberArguments(lottoTicket)));
        }
    }

    private static Object[] makeLottoNumberArguments(LottoTicket lottoTicket) {
        return lottoTicket.getLottoBalls()
                .stream()
                .map(LottoBall::getNumber)
                .toArray();
    }

    public static void printLottoResult(LottoResultBundle lottoResultBundle) {
        System.out.println(RESULT_HEADER);
        printResults(lottoResultBundle);
        System.out.println(String.format(RATE_MESSAGE, lottoResultBundle.getRate()));
    }

    private static void printResults(LottoResultBundle lottoResultBundle) {
        for (Rank rank : Rank.values()) {
            int matchCount = rank.getMatchCount();
            int defaultPrize = rank.getDefaultPrize();
            int matchTicketCount = lottoResultBundle.getMatchTicketCount(rank);
            String message = String.format(findMessage(rank.name()), matchCount, defaultPrize, matchTicketCount);
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
