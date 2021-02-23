package dto;

import domain.ticket.LottoTicket;

import java.util.List;

public class SingleLottoTicketDto {
    private final List<Integer> lottoNumbers;

    private SingleLottoTicketDto(final List<Integer> lottoNumbers) {
        this.lottoNumbers = lottoNumbers;
    }

    public static SingleLottoTicketDto of(final LottoTicket lottoTicket) {
        return new SingleLottoTicketDto(lottoTicket.toIntegerList());
    }

    public List<Integer> getLottoNumbers() {
        return lottoNumbers;
    }
}
