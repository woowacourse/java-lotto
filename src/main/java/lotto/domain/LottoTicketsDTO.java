package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class LottoTicketsDTO {
    private final List<LottoTicketDTO> lottoTickets;

    public LottoTicketsDTO(LottoTickets lottoTickets) {
        this.lottoTickets = new ArrayList<>();
        for (LottoTicket lottoTicket : lottoTickets.getLottoTickets()) {
            this.lottoTickets.add(new LottoTicketDTO(lottoTicket));
        }
    }

    public List<LottoTicketDTO> getLottoTickets() {
        return lottoTickets;
    }
}
