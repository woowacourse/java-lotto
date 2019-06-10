package lotto.domain;

import lotto.dto.WinningLottoDto;
import lotto.domain.TicketModel.*;

public class WinningLotto extends AbstractWinningLotto {

    public WinningLotto(WinningLottoDto dto) {
        super(dto.getNumbers(), dto.getBonus());
    }
}
