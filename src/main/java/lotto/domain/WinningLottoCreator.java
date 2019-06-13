package lotto.domain;

import lotto.domain.core.*;
import lotto.domain.exceptions.LottoNumberException;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class WinningLottoCreator implements WinningTicketCreator {
    private static final String DELIMETER = ",";
    private final TicketCreator ticketCreator;
    private final Ticket winningTicket;
    private final TicketNumber bonus;


    public WinningLottoCreator(String numbers, String bonus) {
        this(numbers, bonus, new LottoCreator());
    }

    public WinningLottoCreator(String numbers, String bonus, TicketCreator ticketCreator) {
        this.ticketCreator = ticketCreator;
        this.winningTicket = ticketCreator.create(parseNumber(numbers));
        this.bonus = ticketCreator.bonus(toInt(bonus));
    }

    @Override
    public WinningTicket create() {
        return new WinningLotto(winningTicket, bonus);
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
}
