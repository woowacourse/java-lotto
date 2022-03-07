package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class LottoTickets {

    private final List<LottoTicket> lottoTickets;

    public LottoTickets(List<LottoTicket> lottoTickets) {
        this.lottoTickets = lottoTickets;
    }

    public List<LottoTicket> getLottoTickets() {
        return new ArrayList<>(lottoTickets);
    }

    public List<Rank> getRanksWithWinningNumbers(WinningNumbers winningNumbers) {
        List<Rank> ranks = new ArrayList<>();
        for (LottoTicket lottoTicket : lottoTickets) {
            ranks.add(lottoTicket.compareNumbers(winningNumbers.getWinningNumbers(), winningNumbers.getBonusNumber()));
        }
        return ranks;
    }

    public LottoTickets combine(LottoTickets lottoTickets) {
        this.lottoTickets.addAll(lottoTickets.getLottoTickets());
        return new LottoTickets(this.lottoTickets);
    }
}
