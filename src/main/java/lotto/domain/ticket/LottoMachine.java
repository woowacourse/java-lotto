package lotto.domain.ticket;

import lotto.domain.result.win.WinningLotto;
import lotto.domain.ticket.ball.LottoBall;
import lotto.domain.ticket.ball.LottoFactory;
import lotto.view.dto.BettingMoneyRequestDTO;
import lotto.view.dto.WinningLottoRequestDTO;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static lotto.domain.ticket.LottoTicket.LOTTO_PRICE;

public abstract class LottoMachine {

    public final WinningLotto createWinningLotto(WinningLottoRequestDTO winningLottoRequestDTO) {
        Set<LottoBall> winningLotto = new HashSet<>();
        for (int number : winningLottoRequestDTO.getWinningNumbers()) {
            winningLotto.add(LottoFactory.getLottoBallByNumber(number));
        }
        return new WinningLotto(winningLotto, LottoFactory.getLottoBallByNumber(winningLottoRequestDTO.getBonusNumber()));
    }

    public final List<LottoTicket> buyTickets(BettingMoneyRequestDTO bettingMoney) {
        int ticketCount = bettingMoney.getBettingMoney() / LOTTO_PRICE;

        List<LottoTicket> lottoTickets = new ArrayList<>();
        for (int i = 0; i < ticketCount; i++) {
            lottoTickets.add(createOneTicket());
        }

        return lottoTickets;
    }

    public abstract LottoTicket createOneTicket();
}
