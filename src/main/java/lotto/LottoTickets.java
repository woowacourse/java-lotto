package lotto;

import java.util.ArrayList;
import java.util.List;

public class LottoTickets {

    private List<LottoTicket> lottoTickets;

    public LottoTickets(int lottoCount) {
        this.lottoTickets = new ArrayList<>();
        createLottoTickets(lottoCount);
    }

    private void createLottoTickets(int lottoCount) {
        LottoNumbers lottoNumbers = new LottoNumbers();
        for (int i = 0; i < lottoCount; i++) {
            lottoTickets.add(new LottoTicket(lottoNumbers.getLottoNumbers(6)));
        }
    }

    public List<LottoTicket> getLottoTickets() {
        return lottoTickets;
    }
}
