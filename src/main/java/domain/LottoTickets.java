package domain;

import java.util.ArrayList;
import java.util.List;

public class LottoTickets {

    private final List<LottoTicket> lottoTickets;

    public LottoTickets() {
        this.lottoTickets = new ArrayList<>();
    }

    public void purchaseManualBy(List<LottoTicket> manualLottoTickets) {
        lottoTickets.addAll(manualLottoTickets);
    }

    public void purchaseAutoBy(int autoLottoCount) {
        for (int i = 0; i < autoLottoCount; i++) {
            lottoTickets.add(LottoTicket.createAutoLotto());
        }
    }

    public List<LottoTicket> getLottoTickets() {
        return new ArrayList<>(lottoTickets);
    }
}
