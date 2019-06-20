package lotto.domain.lottoticket.dto;

import lotto.domain.lottonumber.LottoNumber;

public class WinningLottoDTO {
    private LottoTicketDTO lottoTicketDto;
    private LottoNumber bonusBall;

    public LottoTicketDTO getLottoTicketDto() {
        return lottoTicketDto;
    }

    public void setLottoTicketDto(LottoTicketDTO lottoTicketDto) {
        this.lottoTicketDto = lottoTicketDto;
    }

    public LottoNumber getBonusBall() {
        return bonusBall;
    }

    public void setBonusBall(LottoNumber bonusBall) {
        this.bonusBall = bonusBall;
    }
}
