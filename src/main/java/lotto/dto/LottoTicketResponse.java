package lotto.dto;

import java.util.List;
import java.util.stream.Collectors;

import lotto.domain.LottoNumber;
import lotto.domain.LottoTicket;
import lotto.domain.LottoTickets;

public class LottoTicketResponse {

    private List<Integer> lottoTicketResponse;

    public LottoTicketResponse(List<Integer> lottoTicketResponse) {
        this.lottoTicketResponse = lottoTicketResponse;
    }

    public static List<LottoTicketResponse> from(LottoTickets lottoTickets) {
        List<LottoTicket> tickets = lottoTickets.getTickets();
        return tickets.stream().map(
            ticket -> new LottoTicketResponse(ticket.getNumbers()
                .stream()
                .map(LottoNumber::getNumber)
                .collect(Collectors.toList()))
        ).collect(Collectors.toList());
    }

    public List<Integer> getLottoTicketResponse() {
        return lottoTicketResponse;
    }
}
