package lotto.domain.lottoticket.dto;

import java.util.List;

public class LottoTicketsDto {
    private List<LottoTicketDto> lottoTicketDtos;

    public List<LottoTicketDto> getLottoTicketDtos() {
        return lottoTicketDtos;
    }

    public void setLottoTicketDtos(List<LottoTicketDto> lottoTicketDtos) {
        this.lottoTicketDtos = lottoTicketDtos;
    }

    public void addLottoTicketDto(LottoTicketDto lottoTicketDto) {
        lottoTicketDtos.add(lottoTicketDto);
    }
}
