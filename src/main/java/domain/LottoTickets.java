package domain;

import java.util.ArrayList;
import java.util.List;

public class LottoTickets {

    public static final int LOTTO_PRICE = 1000;
    private final List<LottoTicket> lottoTickets;

    public LottoTickets() {
        this.lottoTickets = new ArrayList<>();
    }

    public void add(LottoTicket lottoTicket) {
        lottoTickets.add(lottoTicket);
    }

    public void purchaseBy(UserBalance userBalance, int manualLottoCount) {
        int lottoCount = userBalance.getUserBalance() / LOTTO_PRICE - manualLottoCount;

        for (int i = 0; i < lottoCount; i++) {
            lottoTickets.add(LottoTicket.createAutoLotto());
        }
    }

    public List<LottoTicket> getLottoTickets() {
        return new ArrayList<>(lottoTickets);
    }
}
