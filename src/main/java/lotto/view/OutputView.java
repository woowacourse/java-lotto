package lotto.view;

import java.util.List;
import lotto.domain.LottoTicket;
import lotto.domain.LottoTickets;

public class OutputView {

    public void printErrorMessage(String errorMessage) {
        System.out.println("[ERROR] " + errorMessage);
    }

    public void printTotalCount(int totalCount) {
        System.out.println(totalCount + "개를 구매했습니다.");
    }

    public void printLottoTicketsInfo(LottoTickets lottoTickets) {
        System.out.println(getTicketsInfo(lottoTickets.getLottoTickets()));
    }

    private String getTicketsInfo(List<LottoTicket> lottoTickets) {
        String ticketsInfo = "";

        for (LottoTicket lottoTicket : lottoTickets) {
            ticketsInfo = ticketsInfo + lottoTicket.getLottoNumbers() + "\n";
        }

        return ticketsInfo;
    }
}
