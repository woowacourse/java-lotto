package lotto.dto.request;

import java.util.Set;

public class LottoTicketDto {

    private Set<Integer> ticketNumbers;

    public LottoTicketDto(Set<Integer> ticketNumbers) {
        this.ticketNumbers = ticketNumbers;
    }

    public Set<Integer> getTicketNumbers() {
        return ticketNumbers;
    }
}
