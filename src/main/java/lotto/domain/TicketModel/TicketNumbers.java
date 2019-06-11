package lotto.domain.TicketModel;

import java.util.List;

public interface TicketNumbers {

    boolean contains(TicketNumber number);

    List<TicketNumber> numbers();

    int matchNumber(TicketNumbers numbers);
}
