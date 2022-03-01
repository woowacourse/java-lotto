package lotto.view;

import static lotto.domain.enumeration.Rank.*;

import java.util.List;
import java.util.Map;
import lotto.domain.enumeration.Rank;
import lotto.domain.vo.LottoNumber;
import lotto.dto.LottoTicketDto;
import lotto.dto.LottoTicketsDto;

public class OutputView {

    private static final String LOTTO_PREFIX = "[";
    private static final String SEPARATOR = ", ";
    private static final String LOTTO_SUFFIX = "]\n";

    public void printErrorMessage(String errorMessage) {
        System.out.println("[ERROR] " + errorMessage);
    }

    public void printTotalCount(int totalCount) {
        System.out.println(totalCount + "개를 구매했습니다.");
    }

    public void printLottoTicketsInfo(LottoTicketsDto lottoTickets) {
        System.out.println(getLottoTicketsInfo(lottoTickets.getLottoTickets()));
    }

    private String getLottoTicketsInfo(List<LottoTicketDto> lottoTickets) {
        String ticketsInfo = "";

        for (LottoTicketDto lottoTicket : lottoTickets) {
            List<LottoNumber> lottoNumbers = lottoTicket.getLottoNumbers();
            ticketsInfo = getLottoTicketInfo(ticketsInfo, lottoNumbers);
        }

        return ticketsInfo;
    }

    private String getLottoTicketInfo(String ticketsInfo, List<LottoNumber> lottoNumbers) {
        ticketsInfo = ticketsInfo + LOTTO_PREFIX;

        for (LottoNumber lottoNumber : lottoNumbers) {
            ticketsInfo = ticketsInfo + lottoNumber.getNumber() + SEPARATOR;
        }

        ticketsInfo = ticketsInfo.substring(0, ticketsInfo.length()-2);
        ticketsInfo = ticketsInfo + LOTTO_SUFFIX;

        return ticketsInfo;
    }

    public void printLottoResultMessage() {
        System.out.println();
        System.out.println("당첨 통계");
        System.out.println("---------");
    }

    public void printYield(Map<Rank, Integer> ranks, double yield) {
        System.out.printf("3개 일치 (%d원)-%d개\n", FIFTH.getPrizeMoney(), ranks.getOrDefault(FIFTH, 0));
        System.out.printf("4개 일치 (%d원)-%d개\n", FORTH.getPrizeMoney(), ranks.getOrDefault(FORTH, 0));
        System.out.printf("5개 일치 (%d원)-%d개\n", THIRD.getPrizeMoney(), ranks.getOrDefault(THIRD, 0));
        System.out.printf("5개 일치, 보너스 볼 일치 (%d원)-%d개\n", SECOND.getPrizeMoney(), ranks.getOrDefault(SECOND, 0));
        System.out.printf("6개 일치 (%d원)-%d개\n", FIRST.getPrizeMoney(), ranks.getOrDefault(FIRST, 0));
        System.out.printf("총 수익률은 %.2f 입니다.", yield);
    }
}
