package domain;

import java.util.Collections;
import java.util.List;

public class LottoManager {

    private final Price price;
    private final List<LottoTicket> lottoTickets;

    private LottoManager(final Price price) {
        this.price = price;
        this.lottoTickets = LottoMachine.generateLottoTickets(price);
    }

    public static LottoManager init(final int price) {
        return new LottoManager(Price.valueOf(price));
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
