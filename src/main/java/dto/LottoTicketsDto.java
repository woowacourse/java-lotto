package dto;

import domain.ticket.LottoTicket;
import domain.tickets.AutoLottoTickets;

import java.util.List;
import java.util.stream.Collectors;

public class LottoTicketsDto {
    private final List<SingleLottoTicketDto> singleLottoTicketDtos;

    private LottoTicketsDto(final List<SingleLottoTicketDto> singleLottoTicketDtos) {
        this.singleLottoTicketDtos = singleLottoTicketDtos;
    }

    public static LottoTicketsDto of(final AutoLottoTickets autoLottoTickets) {
        List<SingleLottoTicketDto> dtos = autoLottoTickets.toList()
                .stream()
                .map(LottoTicket.class::cast)
                .map(SingleLottoTicketDto::of)
                .collect(Collectors.toList());

        return new LottoTicketsDto(dtos);
    }

    public List<SingleLottoTicketDto> getSingleLottoTicketDtos() {
        return singleLottoTicketDtos;
    }
}
