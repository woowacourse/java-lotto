package lotto.domain;

import java.util.List;
import java.util.stream.Collectors;

public class ManualTickets {

    private final List<ManualTicket> manualTickets;

    private ManualTickets(List<ManualTicket> manualTickets) {
        this.manualTickets = manualTickets;
    }

    public static ManualTickets from(List<String[]> inputNumbers) {
        List<ManualTicket> manualTickets = inputNumbers.stream()
                .map(ManualTicket::from)
                .collect(Collectors.toList());
        return new ManualTickets(manualTickets);
    }

    public int size() {
        return manualTickets.size();
    }

    public List<ManualTicket> getManualTickets() {
        return manualTickets;
    }
}
