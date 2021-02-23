package lotto.dto;

import java.util.Collections;
import java.util.List;

public class TicketNumbersDto {

    private final List<Integer> ticketNumbers;

    public TicketNumbersDto(List<Integer> ticketNumbers) {
        this.ticketNumbers = ticketNumbers;
    }

    public List<Integer> getTicketNumbers() {
        return Collections.unmodifiableList(ticketNumbers);
    }
}
