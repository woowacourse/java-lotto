package dto;

import domain.ticket.LottoTicket;
import domain.tickets.LottoTickets;

import java.util.List;
import java.util.stream.Collectors;

public class LottoTicketsDto {
    private final List<LottoTicketDto> lottoTicketDtos;

    private LottoTicketsDto(final List<LottoTicketDto> lottoTicketDtos) {
        this.lottoTicketDtos = lottoTicketDtos;
    }

    public static LottoTicketsDto from(final LottoTickets lottoTickets) {
        List<LottoTicketDto> dtos = lottoTickets.toList()
                .stream()
                .map(LottoTicket.class::cast)
                .map(LottoTicketDto::from)
                .collect(Collectors.toList());

        return new LottoTicketsDto(dtos);
    }

    public List<LottoTicketDto> getLottoTicketDtos() {
        return lottoTicketDtos;
    }
}
