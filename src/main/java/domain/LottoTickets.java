package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoTickets {

    public static final int LOTTO_PRICE = 1000;
    private final List<LottoTicket> lottoTickets;

    public LottoTickets(List<LottoTicket> lottoTickets) {
        this.lottoTickets = lottoTickets;
    }

    public static LottoTickets purchaseBy(UserBalance userBalance) {
        List<LottoTicket> lottoTickets = new ArrayList<>();
        int lottoCount = userBalance.getUserBalance() / LOTTO_PRICE;

        for (int i = 0; i < lottoCount; i++) {
            lottoTickets.add(LottoTicket.createAutoLotto());
        }

        return new LottoTickets(lottoTickets);
    }

    public List<LottoTicket> getLottoTickets() {
        return Collections.unmodifiableList(lottoTickets);
    }
}
