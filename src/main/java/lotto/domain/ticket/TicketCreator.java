package lotto.domain.ticket;

import java.util.List;

public interface TicketCreator {
    Ticket create();

    Ticket create(List<Integer> numbers);

    int unitPrice();
}
