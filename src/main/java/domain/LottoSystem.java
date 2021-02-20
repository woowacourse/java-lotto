package domain;

import java.util.List;

public class LottoSystem {
    private final LottoMoney lottoMoney;
    private final List<LottoTicket> lottoTickets;

    private LottoSystem(final LottoMoney lottoMoney) {
        this.lottoMoney = lottoMoney;
        final LottoMachine lottoMachine = LottoMachine.valueOf(lottoMoney);
        this.lottoTickets = lottoMachine.generateLottoTickets();
    }

    public static LottoSystem init(final String price) {
        return new LottoSystem(LottoMoney.valueOf(price));
    }

    public List<LottoTicket> getLottoTickets() {
        return lottoTickets;
    }

    public int getLottoQuantity() {
        return lottoTickets.size();
    }

    public WinningResult getWinningResult(final WinningNumbers winningNumbers) {
        return new WinningResult(winningNumbers, lottoTickets, lottoMoney);
    }
}
