package lotto.domain.TicketModel;

import lotto.dto.WinningLottoDto;

import java.util.List;

public class WinningLotto implements WinningTicket {
    private final Ticket winningTicket;
    private final int bonus;

    public WinningLotto(WinningLottoDto dto, TicketCreator ticketCreator) {
        this.winningTicket = ticketCreator.create(dto.getNumbers());
        this.bonus = dto.getBonus();
    }

    @Override
    public int match(Ticket lotto) {
        return match(lotto.numbers());
    }

    private int match(List<Integer> lottoNumbers) {
        return (int) lottoNumbers.stream()
                .filter(number -> winningTicket.contains(number))
                .count();
    }

    @Override
    public boolean bonus(Ticket lotto) {
        return bonus(lotto.numbers());
    }

    private boolean bonus(List<Integer> numbers) {
        return numbers.contains(bonus);
    }
}
