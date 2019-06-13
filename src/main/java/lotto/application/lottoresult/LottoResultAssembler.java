package lotto.application.lottoresult;

import lotto.application.lottoticket.LottoTicketService;
import lotto.domain.lottoresult.WinningLotto;
import lotto.domain.lottoticket.dto.LottoTicketDTO;
import lotto.domain.lottoticket.dto.WinningLottoDTO;

class LottoResultAssembler {
    static WinningLottoDTO getWinningLottoDto(WinningLotto winningLotto) {
        WinningLottoDTO winningLottoDto = new WinningLottoDTO();

        LottoTicketDTO lottoTicketDto = LottoTicketService.getLottoTicketDto(winningLotto.getWinningTicket());
        winningLottoDto.setLottoTicketDto(lottoTicketDto);
        winningLottoDto.setBonusBall(winningLotto.getBonusBall());

        return winningLottoDto;
    }
}
