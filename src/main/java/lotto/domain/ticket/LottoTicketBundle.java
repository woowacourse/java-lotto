package lotto.domain.ticket;

import lotto.domain.result.LottoResultBundle;
import lotto.domain.result.win.WinningLotto;

import java.util.List;
import java.util.stream.Collectors;

public class LottoTicketBundle {
    private final List<LottoTicket> lottoTickets;

    public LottoTicketBundle(List<LottoTicket> lottoTickets) {
        this.lottoTickets = lottoTickets;
    }

    public LottoResultBundle getLottoResults(WinningLotto winningLotto) {
        return lottoTickets.stream()
                .map(winningLotto::getResult)
                .collect(Collectors.collectingAndThen(Collectors.toList(), LottoResultBundle::new));
    }
}
