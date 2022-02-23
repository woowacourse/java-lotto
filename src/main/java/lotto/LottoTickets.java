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
        LottoNumberGenerator lottoNumberGenerator = new LottoNumberGenerator();
        for (int i = 0; i < lottoCount; i++) {
            lottoTickets.add(new LottoTicket(lottoNumberGenerator.getLottoNumbers(6)));
        }
    }

    public List<LottoTicket> getLottoTickets() {
        return lottoTickets;
    }
}
