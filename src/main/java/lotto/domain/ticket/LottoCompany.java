package lotto.domain.ticket;

import lotto.domain.result.win.WinningLotto;
import lotto.domain.ticket.ball.LottoBall;
import lotto.domain.ticket.ball.LottoFactory;
import lotto.view.dto.BettingMoneyRequestDTO;
import lotto.view.dto.WinningLottoRequestDTO;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class LottoCompany {

    public abstract List<LottoTicket> buyTicket(BettingMoneyRequestDTO bettingMoney);

    public static WinningLotto makeWinningLotto(WinningLottoRequestDTO winningLottoRequestDTO) {
        Set<LottoBall> winningLotto = new HashSet<>();
        for (int number : winningLottoRequestDTO.getWinningNumbers()) {
            winningLotto.add(LottoFactory.getLottoBallByNumber(number));
        }
        return new WinningLotto(winningLotto, LottoFactory.getLottoBallByNumber(winningLottoRequestDTO.getBonusNumber()));
    }


}
