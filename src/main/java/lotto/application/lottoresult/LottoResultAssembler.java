package lotto.application.lottoresult;

import lotto.application.lottoticket.LottoTicketService;
import lotto.domain.lottoresult.WinningLotto;
import lotto.domain.lottoticket.dto.LottoTicketDto;
import lotto.domain.lottoticket.dto.WinningLottoDto;

class LottoResultAssembler {
    static WinningLottoDto getWinningLottoDto(WinningLotto winningLotto) {
        WinningLottoDto winningLottoDto = new WinningLottoDto();

        LottoTicketDto lottoTicketDto = LottoTicketService.getLottoTicketDto(winningLotto.getWinningTicket());
        winningLottoDto.setLottoTicketDto(lottoTicketDto);
        winningLottoDto.setBonusBall(winningLotto.getBonusBall());

        return winningLottoDto;
    }
}
