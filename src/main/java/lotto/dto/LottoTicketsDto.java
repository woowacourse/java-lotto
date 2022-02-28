package lotto.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lotto.domain.LottoTickets;

public class LottoTicketsDto {

    private final List<LottoTicketDto> lottoTickets;

    public LottoTicketsDto(List<LottoTicketDto> lottoTickets) {
        this.lottoTickets = lottoTickets;
    }

    public static LottoTicketsDto from(LottoTickets lottoTickets) {
        return new LottoTicketsDto(lottoTickets.getLottoTickets().stream()
                .map(LottoTicketDto::from)
                .collect(Collectors.toList()));
    }

    public List<LottoTicketDto> getLottoTickets() {
        return new ArrayList<>(lottoTickets);
    }
}
