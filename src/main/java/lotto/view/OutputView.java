package lotto.view;

import lotto.domain.LottoTicket;
import lotto.domain.LottoTickets;

public class OutputView {
    private OutputView(){
    }

    public static void printLottoTickets(LottoTickets lottoTickets) {
        for (LottoTicket lottoTicket : lottoTickets.lottoTickets()){
            System.out.println(lottoTicket.lottoTicket());
        }
    }
}
