package lotto.domain.dto;

import java.util.ArrayList;
import java.util.List;
import lotto.domain.Lotto;

public class LottoTicketsDTO {
    final private List<Lotto> lottoTickets;

    public LottoTicketsDTO(List<Lotto> lottoTickets) {
        this.lottoTickets = new ArrayList<>(lottoTickets);
    }

    public List<Lotto> getLottoTickets() {
        return lottoTickets;
    }
}
