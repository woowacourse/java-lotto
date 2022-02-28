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
                .map(LottoTicketDto::from)
                .collect(toList());
    }

    private LottoTicketsDto(List<List<Integer>> lottoNumbers) {
        this.lottoTickets = lottoNumbers.stream()
                .map(LottoTicketDto::from)
                .collect(toList());
    }

    public static LottoTicketsDto from(LottoTickets lottoTickets) {
        return new LottoTicketsDto(lottoTickets);
    }

    public static LottoTicketsDto from(List<List<Integer>> lottoNumbers) {
        return new LottoTicketsDto(lottoNumbers);
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
