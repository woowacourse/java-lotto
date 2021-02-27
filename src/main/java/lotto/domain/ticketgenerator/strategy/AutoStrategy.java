package lotto.domain.ticketgenerator.strategy;

import lotto.domain.ticket.AutoTickets;
import lotto.domain.ticket.LottoNumber;
import lotto.domain.ticket.LottoTicket;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AutoStrategy implements GenerateStrategy {
    public static final int ZERO = 0;

    private final int ticketCount;
    private final List<LottoNumber> allNumbers = new ArrayList<>();

    public AutoStrategy(int ticketCount) {
        this.ticketCount = ticketCount;
        initAllNumbers();
    }

    private void initAllNumbers() {
        for (int i = LottoNumber.MINIMUM; i <= LottoNumber.MAXIMUM; i++) {
            allNumbers.add(new LottoNumber(i));
        }
    }

    @Override
    public AutoTickets generateTickets() {
        List<LottoTicket> tickets = new ArrayList<>();
        for (int i = 0; i < ticketCount; i++) {
            tickets.add(generateTicket());
        }
        return new AutoTickets(tickets);
    }

    private LottoTicket generateTicket() {
        Collections.shuffle(allNumbers);
        List<LottoNumber> numbers = new ArrayList<>(allNumbers.subList(ZERO, LottoTicket.SIZE));
        return new LottoTicket(numbers);
    }
}
