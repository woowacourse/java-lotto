package lotto.model;

import java.util.ArrayList;
import java.util.List;

public class ManualTicketsGenerator implements TicketsGenerator {
    private List<String> inputTickets;

    public ManualTicketsGenerator(List<String> inputTickets) {
        this.inputTickets = inputTickets;
    }

    @Override
    public List<Ticket> generate(LottoCount lottoCount) {
        List<Ticket> manualTickets = new ArrayList<>();
        for (int i = 0; i < lottoCount.getManualTicketCount(); i++) {
            manualTickets.add(new Ticket(inputTickets.get(i)));
        }
        return manualTickets;
    }
}
