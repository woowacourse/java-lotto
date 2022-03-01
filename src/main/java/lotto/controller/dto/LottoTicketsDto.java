package lotto.controller.dto;

import static java.util.stream.Collectors.*;

import java.util.Collections;
import java.util.List;
import lotto.domain.LottoTickets;

public class LottoTicketsDto {

    private final List<LottoTicketDto> lottoTickets;

    private LottoTicketsDto(LottoTickets lottoTickets) {
        this.lottoTickets = lottoTickets.getLottoTickets()
                .stream()
                .map(LottoTicketDto::from)
                .collect(toList());
    }

    public static LottoTicketsDto from(LottoTickets lottoTickets) {
        return new LottoTicketsDto(lottoTickets);
    }

    public LottoTickets toLottoTickets() {
        List<List<Integer>> lottoNumbers = lottoTickets.stream()
                .map(LottoTicketDto::getLottoNumbers)
                .collect(toList());

        return LottoTickets.createManualLottoTickets(lottoTickets.size(), lottoNumbers);
    }

    public List<LottoTicketDto> getLottoTickets() {
        return Collections.unmodifiableList(lottoTickets);
    }
}
