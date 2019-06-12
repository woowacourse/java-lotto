package lotto.domain.lottoticket.dto;

import lotto.domain.lottonumber.LottoNumber;

public class WinningLottoDto {
    private LottoTicketDto lottoTicketDto;
    private LottoNumber bonusBall;

    public LottoTicketDto getLottoTicketDto() {
        return lottoTicketDto;
    }

    public void setLottoTicketDto(LottoTicketDto lottoTicketDto) {
        this.lottoTicketDto = lottoTicketDto;
    }

    public LottoNumber getBonusBall() {
        return bonusBall;
    }

    public void setBonusBall(LottoNumber bonusBall) {
        this.bonusBall = bonusBall;
    }
}
