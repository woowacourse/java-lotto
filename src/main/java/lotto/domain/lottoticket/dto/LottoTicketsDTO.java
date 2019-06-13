package lotto.domain.lottoticket.dto;

import java.util.List;

public class LottoTicketsDTO {
    private List<LottoTicketDTO> lottoTicketDTOS;

    public List<LottoTicketDTO> getLottoTicketDTOS() {
        return lottoTicketDTOS;
    }

    public void setLottoTicketDTOS(List<LottoTicketDTO> lottoTicketDTOS) {
        this.lottoTicketDTOS = lottoTicketDTOS;
    }

    public void addLottoTicketDto(LottoTicketDTO lottoTicketDto) {
        lottoTicketDTOS.add(lottoTicketDto);
    }
}
