package lotto.model;

import java.util.ArrayList;
import java.util.List;

public class AutoTickets {

    private static List<AutoTicket> autoTickets = new ArrayList<>();

    public AutoTickets(int ticketCount) {
        for (int i = 0; i < ticketCount; i++) {
            autoTickets.add(new AutoTicket());
        }
    }

    public static List<AutoTicket> getAutoTickets() {
        return autoTickets;
    }
}
