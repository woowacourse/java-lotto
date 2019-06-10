package lotto.domain.TicketModel;

import lotto.dto.WinningLottoDto;

public class WinningLotto implements WinningTicket {
    private final Ticket winningTicket;
    private final int bonus;

    public WinningLotto(WinningLottoDto dto, TicketCreator ticketCreator) {
        this.winningTicket = ticketCreator.create(dto.getNumbers());
        this.bonus = dto.getBonus();
    }

    @Override
    public int match(Ticket lotto) {
        return containsNumber(lotto.numbers());
    }

    private int containsNumber(LottoNumbers lottoNumbers) {
        return (int) lottoNumbers.numbers().stream()
                .filter(number -> winningTicket.contains(number))
                .count();
    }

    @Override
    public boolean bonus(Ticket lotto) {
        return lotto.contains(bonus);
    }
}
