package lotto.domain.lottoticket.dto;

import java.util.List;

public class LottoTicketsDTO {
    private List<LottoTicketDTO> lottoTicketDTOs;

    public List<LottoTicketDTO> getLottoTicketDTOs() {
        return lottoTicketDTOs;
    }

    public void setLottoTicketDTOs(List<LottoTicketDTO> lottoTicketDTOs) {
        this.lottoTicketDTOs = lottoTicketDTOs;
    }
}
