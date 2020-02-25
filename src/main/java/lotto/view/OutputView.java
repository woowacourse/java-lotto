package lotto.view;

import lotto.domain.result.LottoResultBundle;
import lotto.domain.result.win.rank.Rank;
import lotto.domain.ticket.LottoTicket;
import lotto.domain.ticket.LottoTicketBundle;
import lotto.domain.ticket.ball.LottoNumber;

public class OutputView {
    private static final String SECOND = "SECOND";

    public static void printBuyTicketCount(int randomCount, int manualCount) {
        System.out.println(String.format("수동으로 %d장, 자동으로 %d개를 구매했습니다.", manualCount, randomCount));
    }

    public static void printBuyTickets(LottoTicketBundle lottoTicketBundle) {
        for (LottoTicket lottoTicket : lottoTicketBundle.getLottoTickets()) {
            System.out.println(String.format("[ %d %d %d %d %d %d ]", makeLottoNumberArguments(lottoTicket)));
        }
    }

    private static Object[] makeLottoNumberArguments(LottoTicket lottoTicket) {
        return lottoTicket.getLottoNumbers()
                .stream()
                .map(LottoNumber::getNumber)
                .toArray();
    }

    public static void printLottoResult(LottoResultBundle lottoResultBundle) {
        System.out.println("당첨 통계\n-----------");
        printResults(lottoResultBundle);
        System.out.println(String.format("총 수익률은 %s입니다.", lottoResultBundle.getRate()));
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
            return "%d개 일치, 보너스 볼 일치(%d원) - %d개";
        }
        return "%d개 일치 (%d원)- %d개";
    }

    private static boolean isSecond(String name) {
        return SECOND.equals(name);
    }
}
