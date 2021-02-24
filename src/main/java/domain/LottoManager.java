package domain;

import java.util.Collections;
import java.util.List;

public class LottoManager {

    private final Money money;
    private final List<LottoTicket> lottoTickets;

    private LottoManager(final Money money) {
        this.money = money;
        this.lottoTickets = LottoMachine.generateLottoTickets(money);
    }

    public static LottoManager init(final int price) {
        return new LottoManager(Money.valueOf(price));
    }

    public List<LottoTicket> getLottoTickets() {
        return Collections.unmodifiableList(lottoTickets);
    }

    public int getLottoQuantity() {
        return lottoTickets.size();
    }

    public WinningResult getWinningResult(final WinningNumbers winningNumbers) {
        return new WinningResult(winningNumbers, lottoTickets, money);
    }
}
