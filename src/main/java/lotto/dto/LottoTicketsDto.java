package lotto.dto;

import static java.util.stream.Collectors.toList;

import java.util.Collections;
import java.util.List;
import lotto.domain.LottoTickets;

public class LottoTicketsDto {

    private final List<LottoTicketDto> lottoTickets;

    public LottoTicketsDto(LottoTickets lottoTickets) {
        this.lottoTickets = lottoTickets.getLottoTickets()
                .stream()
                .map(LottoTicketDto::new)
                .collect(toList());
    }

    public List<LottoTicketDto> getLottoTickets() {
        return Collections.unmodifiableList(lottoTickets);
    }

}
