package dto;

import domain.ticket.LottoTicket;
import domain.tickets.AutoLottoTickets;

import java.util.List;
import java.util.stream.Collectors;

public class LottoTicketsDto {
    private final List<LottoTicketDto> lottoTicketDtos;

    private LottoTicketsDto(final List<LottoTicketDto> lottoTicketDtos) {
        this.lottoTicketDtos = lottoTicketDtos;
    }

    public static LottoTicketsDto of(final AutoLottoTickets autoLottoTickets) {
        List<LottoTicketDto> dtos = autoLottoTickets.toList()
                .stream()
                .map(LottoTicket.class::cast)
                .map(LottoTicketDto::of)
                .collect(Collectors.toList());

        return new LottoTicketsDto(dtos);
    }

    public List<LottoTicketDto> getLottoTicketDtos() {
        return lottoTicketDtos;
    }
}
