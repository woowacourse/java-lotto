package view;

import domain.LottoTicket;
import java.util.List;

public class OutputView {
    public static void printLottoTickets(List<LottoTicket> lottoTickets) {
        System.out.printf("%d개를 구매했습니다.\n", lottoTickets.size());

        for (LottoTicket lottoTicket : lottoTickets) {
            System.out.println(lottoTicket.getNumbers());
        }
    }
}
