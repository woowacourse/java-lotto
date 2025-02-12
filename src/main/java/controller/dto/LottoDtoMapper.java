package controller.dto;

import java.util.List;
import model.LottoTicket;

public class LottoDtoMapper {

    public List<LottoTicketResponse> toLottoTicketResponse(List<LottoTicket> lottoTickets) {
        return lottoTickets.stream()
                .map(ticket -> LottoTicketResponse.from(ticket.getNumbers()))
                .toList();
    }
}
