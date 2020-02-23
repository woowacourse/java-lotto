package domain;

import java.util.ArrayList;
import java.util.List;

public class LottoTicketsGenerator {
    private LottoTicketsGenerator() {
    }

    public static List<LottoTicket> generateLottoTickets(int lottoTicketCount) {
        List<LottoTicket> lottoTickets = new ArrayList<>();
        for (int i = 0; i < lottoTicketCount; i++) {
            lottoTickets.add(LottoTicketGenerator.generateLottoTicket());
        }
        return lottoTickets;
    }
}
