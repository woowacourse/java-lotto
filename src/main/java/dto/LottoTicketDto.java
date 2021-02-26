package dto;

import domain.ticket.LottoTicket;

import java.util.List;

public class LottoTicketDto {
    private final List<Integer> lottoNumbers;

    private LottoTicketDto(final List<Integer> lottoNumbers) {
        this.lottoNumbers = lottoNumbers;
    }

    public static LottoTicketDto from(final LottoTicket lottoTicket) {
        return new LottoTicketDto(lottoTicket.toIntegerList());
    }

    public List<Integer> getLottoNumbers() {
        return lottoNumbers;
    }
}
