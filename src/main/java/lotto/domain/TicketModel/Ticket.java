package lotto.domain.TicketModel;

import java.util.List;

public interface Ticket {
    public List<Integer> numbers();

    public boolean contains(int number);
}
