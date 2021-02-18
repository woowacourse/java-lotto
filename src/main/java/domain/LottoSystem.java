package domain;

import java.util.List;

public class LottoSystem {

    private final Price price;
    private final List<LottoTicket> lottoTickets;

    private LottoSystem(final Price price, final WinningNumbers winningNumbers) {
        this.price = price;
        LottoMachine lottoMachine = LottoMachine.valueOf(price);
        this.lottoTickets = lottoMachine.generateLottoTickets();
    }

    public static LottoSystem init(final String price, final List<Integer> winningNumbers,
            final int bonusNumber) {
        return new LottoSystem(Price.valueOf(price), WinningNumbers.valueOf(winningNumbers, bonusNumber));
    }

    public List<LottoTicket> getLottoTickets() {
        return lottoTickets;
    }

    public int getLottoQuantity() {
        return lottoTickets.size();
    }

    public WinningResult getWinningResult(final WinningNumbers winningNumbers) {
        return new WinningResult(winningNumbers, lottoTickets, price);
    }
}
