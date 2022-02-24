package lotto.view;

import java.util.Collections;
import java.util.List;
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
}
