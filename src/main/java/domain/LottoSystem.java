package domain;

import java.util.Collections;
import java.util.List;

public class LottoSystem {

    private final Price price;
    private final List<LottoTicket> lottoTickets;

    private LottoSystem(final Price price) {
        this.price = price;
        this.lottoTickets = LottoMachine.generateLottoTickets(price);
    }

    public static LottoSystem init(final String price) {
        return new LottoSystem(Price.valueOf(price));
    }

    public List<LottoTicket> getLottoTickets() {
        return Collections.unmodifiableList(lottoTickets);
    }

    public int getLottoQuantity() {
        return lottoTickets.size();
    }

    public WinningResult getWinningResult(final WinningNumbers winningNumbers) {
        return new WinningResult(winningNumbers, lottoTickets, price);
    }
}
