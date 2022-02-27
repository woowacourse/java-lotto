package lotto.controller.dto;

import static java.util.stream.Collectors.*;

import java.util.Collections;
import java.util.List;
import lotto.domain.LottoTicket;
import lotto.domain.LottoTickets;

public class LottoTicketsDto {

    private final List<LottoTicketDto> lottoTickets;

    private LottoTicketsDto(LottoTickets lottoTickets) {
        this.lottoTickets = lottoTickets.getLottoTickets()
                .stream()
                .map(LottoTicketDto::new)
                .collect(toList());
    }

    public static LottoTicketsDto from(LottoTickets lottoTickets) {
        return new LottoTicketsDto(lottoTickets);
    }

    public LottoTickets toLottoTickets() {
        List<LottoTicket> lottoTickets = this.lottoTickets.stream()
                .map(LottoTicketDto::toLottoTicket)
                .collect(toList());
        return new LottoTickets(lottoTickets);
    }

    public List<LottoTicketDto> getLottoTickets() {
        return Collections.unmodifiableList(lottoTickets);
    }
}
