package lotto.model;

import java.util.ArrayList;
import java.util.List;

public class LottoMerge {

    public static List<Ticket> merge(List<Ticket> manualTickets, List<Ticket> autoTickets) {
        List<Ticket> tickets = new ArrayList<>();
        tickets.addAll(manualTickets);
        tickets.addAll(autoTickets);
        return tickets;
    }
}
