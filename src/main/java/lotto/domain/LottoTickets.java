package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoTickets {
    private final List<LottoTicket> lottoTickets;

    public LottoTickets(List<LottoTicket> lottoTickets) {
        this.lottoTickets = new ArrayList<>(lottoTickets);
    }

    public List<LottoTicket> lottoTickets() {
        return Collections.unmodifiableList(lottoTickets);
    }

    public List<Prize> checkWinningTickets(WinningLotto winningLotto) {
        List<Prize> winningTickets = new ArrayList<>();
        for (LottoTicket lottoTicket : this.lottoTickets) {
            winningTickets.add(Prize.getPrizeType(lottoTicket.getMatchingCount(winningLotto.getWinningTicket().lottoTicket()),
                    lottoTicket.isMatchingBonusNumber(winningLotto.getBonusNumber())));
        }
        return winningTickets;
    }
}
