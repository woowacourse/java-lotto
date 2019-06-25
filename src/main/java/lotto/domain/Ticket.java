package lotto.domain;

import java.util.List;

public interface Ticket {

    List<Integer> ticketNumbers();

    boolean contains(int number);
}
