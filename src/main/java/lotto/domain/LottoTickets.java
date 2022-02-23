package lotto.domain;

import java.util.ArrayList;
import java.util.List;
import lotto.domain.generator.LottoNumberGenerator;

public class LottoTickets {

    private final List<LottoTicket> lottoTickets;

    public LottoTickets(int lottoCount, LottoNumberGenerator lottoNumberGenerator) {
        this.lottoTickets = generateTickets(lottoCount, lottoNumberGenerator);
    }

    private List<LottoTicket> generateTickets(int lottoCount, LottoNumberGenerator lottoNumberGenerator) {
        List<LottoTicket> lottoTickets = new ArrayList<>();

        for (int i = 0; i < lottoCount; i++) {
            LottoTicket lottoTicket = new LottoTicket(lottoNumberGenerator);
            lottoTickets.add(lottoTicket);
        }

        return lottoTickets;
    }

    public int totalCount() {
        return lottoTickets.size();
    }
}
