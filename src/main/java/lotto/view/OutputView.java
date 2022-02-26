package lotto.view;

import static lotto.domain.enumeration.Rank.*;

import java.util.List;
import java.util.Map;
import lotto.domain.enumeration.Rank;
import lotto.domain.vo.LottoNumber;
import lotto.dto.LottoTicketDto;
import lotto.dto.LottoTicketsDto;

public class OutputView {

    public static final String LOTTO_PREFIX = "[";
    public static final String SEPARATOR = ", ";
    public static final String LOTTO_ENDFIX = "]\n";

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
        ticketsInfo = ticketsInfo + LOTTO_ENDFIX;

        return ticketsInfo;
    }

    public void printLottoResultMessage() {
        System.out.println();
        System.out.println("당첨 통계");
        System.out.println("---------");
    }

    public void printYield(Map<Rank, Integer> ranks, double yield) {
        System.out.println("3개 일치 (5000원)-" + ranks.getOrDefault(FIFTH, 0) + "개");
        System.out.println("4개 일치 (50000원)-" + ranks.getOrDefault(FORTH, 0) + "개");
        System.out.println("5개 일치 (1500000원)-" + ranks.getOrDefault(THIRD, 0) + "개");
        System.out.println("5개 일치, 보너스 볼 일치(30000000원)-" + ranks.getOrDefault(SECOND, 0) + "개");
        System.out.println("6개 일치 (2000000000원)-" + ranks.getOrDefault(FIRST, 0) + "개");
        System.out.printf("총 수익률은 %.2f 입니다.(기준이 1이기 때문에 결과적으로 손해라는 의미임)", yield);
    }
}
