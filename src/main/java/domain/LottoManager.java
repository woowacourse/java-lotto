package domain;

import java.util.Collections;
import java.util.List;

public class LottoManager {

    private final Money money;
    private final List<LottoTicket> lottoTickets;

    private LottoManager(final Money money, final List<String> manualTicketNumbers) {
        this.money = money;
        this.lottoTickets = ManualLottoManager.generate(money, manualTicketNumbers);
        this.lottoTickets.addAll(LottoMachine.generateAutoLottoTickets(money.numberOfAutoTicket(lottoTickets.size())));
    }

    public static LottoManager init(final int money, final List<String> manualTicketNumbers) {
        return new LottoManager(Money.valueOf(money), manualTicketNumbers);
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
