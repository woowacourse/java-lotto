package lotto.service;

import lotto.domain.lotto.LottoTicketFactory;
import lotto.domain.lotto.WinningLotto;
import lotto.dto.request.WinningLottoDto;

import java.util.Set;

public class LottoService {

    public WinningLotto createWinningLotto(WinningLottoDto winningLottoDto) {
        Set<Integer> winningLottoNumbers = winningLottoDto.getWinningLottoNumbers();
        Integer bonusNumber = winningLottoDto.getBonusNumber();
        return LottoTicketFactory.publishWinningLotto(winningLottoNumbers, bonusNumber);
    }
}
