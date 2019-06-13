package lotto.domain.core;

import lotto.domain.LottoResult;

import java.util.List;

public interface UserTickets {
    List<Ticket> tickets();

    LottoResult result(WinningTicket winningTicket);
}
