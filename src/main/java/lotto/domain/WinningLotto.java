package lotto.domain;

import lotto.domain.ticket.*;
import lotto.dto.WinningLottoDto;

public class WinningLotto implements WinningTicket {
    private final Ticket winningTicket;
    private final TicketNumber bonus;

    public WinningLotto(WinningLottoDto dto, TicketCreator ticketCreator) {
        this.winningTicket = ticketCreator.create(dto.getNumbers());
        this.bonus = new LottoNumber(dto.getBonus());
    }

    @Override
    public int match(Ticket lotto) {
        return winningTicket.lottoNumbers().matchNumber(lotto.lottoNumbers());
    }

    @Override
    public boolean bonus(Ticket lotto) {
        return lotto.lottoNumbers().contains(bonus);
    }
}
