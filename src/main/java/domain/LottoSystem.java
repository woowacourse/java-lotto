package domain;

import java.util.List;

public class LottoSystem {

    private final List<LottoTicket> lottoTickets;

    public LottoSystem(final Wallet wallet) {
        this.lottoTickets = LottoTicketFactory.generateAuto(wallet);
    }

    public List<LottoTicket> getLottoTickets() {
        return lottoTickets;
    }

    public int getLottoQuantity() {
        return lottoTickets.size();
    }

    public WinningResult getWinningResult(final WinningNumbers winningNumbers) {
        return new WinningResult(winningNumbers, lottoTickets);
    }
}
