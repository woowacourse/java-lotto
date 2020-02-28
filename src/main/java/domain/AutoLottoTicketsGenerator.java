package domain;

import java.util.ArrayList;
import java.util.List;

public class AutoLottoTicketsGenerator {
    private AutoLottoTicketsGenerator() {
    }

    public static List<LottoTicket> generateAutoLottoTickets(int autoLottoTicketCount) {
        List<LottoTicket> autoLottoTickets = new ArrayList<>();
        for (int i = 0; i < autoLottoTicketCount; i++) {
            autoLottoTickets.add(AutoLottoTicketGenerator.generateAutoLottoTicket());
        }
        return autoLottoTickets;
    }
}
