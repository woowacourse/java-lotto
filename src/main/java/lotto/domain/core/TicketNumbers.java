package lotto.domain.core;

import java.util.List;

public interface TicketNumbers {

    boolean contains(TicketNumber number);

    List<TicketNumber> numbers();
}
