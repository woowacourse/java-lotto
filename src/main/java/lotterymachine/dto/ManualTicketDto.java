package lotterymachine.dto;

import java.util.List;

public class ManualTicketDto {
    private final List<Integer> tickets;

    public ManualTicketDto(List<Integer> tickets) {
        this.tickets = tickets;
    }

    public List<Integer> getTickets() {
        return this.tickets;
    }
}
