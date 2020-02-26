package lotto.service;

import lotto.domain.lotto.LottoFactory;
import lotto.domain.lotto.LottoTicket;
import lotto.domain.lotto.WinningLotto;
import lotto.dto.request.LottoTicketDto;
import lotto.dto.request.WinningLottoDto;

import java.util.Set;

public class LottoService {

    public LottoTicket createLottoTicket(LottoTicketDto lottoTicketDto) {
        return LottoFactory.publishLottoTicketFrom(lottoTicketDto.getTicketNumbers());
    }

    public WinningLotto createWinningLotto(WinningLottoDto winningLottoDto) {
        Set<Integer> winningLottoNumbers = winningLottoDto.getWinningLottoNumbers();
        Integer bonusNumber = winningLottoDto.getBonusNumber();
        return LottoFactory.publishWinningLotto(winningLottoNumbers, bonusNumber);
    }
}
