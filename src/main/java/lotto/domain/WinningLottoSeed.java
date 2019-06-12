package lotto.domain;

import lotto.domain.exceptions.LottoNumberException;
import lotto.domain.ticket.LottoCreator;
import lotto.domain.ticket.Ticket;
import lotto.domain.ticket.TicketCreator;
import lotto.domain.ticket.TicketNumber;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class WinningLottoSeed {
    private static final String DELIMETER = ",";
    private final TicketCreator ticketCreator;
    private final Ticket winningTicket;
    private final TicketNumber bonus;


    public WinningLottoSeed(String numbers, String bonus) {
        this(numbers, bonus, new LottoCreator());
    }

    public WinningLottoSeed(String numbers, String bonus, TicketCreator ticketCreator) {
        this.ticketCreator = ticketCreator;
        this.winningTicket = ticketCreator.create(parseNumber(numbers));
        this.bonus = ticketCreator.bonus(toInt(bonus));
    }

    private List<Integer> parseNumber(String text) {
        try {
            return Arrays.stream(text.split(DELIMETER))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
        } catch (NumberFormatException e) {
            throw new LottoNumberException();
        }
    }

    private int toInt(String number) {
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException e) {
            throw new LottoNumberException();
        }
    }

    public Ticket getWinningTicket() {
        return winningTicket;
    }

    public TicketNumber getBonus() {
        return bonus;
    }
}
