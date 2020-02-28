package lotto.model;

import java.util.List;

public interface TicketsGenerator {
    List<Ticket> generate(int count);
}
