package lotto.domain.core;

import java.util.List;

public interface TicketCreator {
    Ticket create();

    Ticket create(List<Integer> numbers);

    TicketNumber bonus(int bonus);

    int unitPrice();
}
