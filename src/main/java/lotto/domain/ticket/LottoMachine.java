package lotto.domain.ticket;

import lotto.domain.result.win.WinningLotto;
import lotto.domain.ticket.ball.LottoBall;
import lotto.domain.ticket.ball.LottoBallFactory;
import lotto.view.dto.BettingMoneyDTO;
import lotto.view.dto.WinningLottoDTO;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static lotto.domain.ticket.LottoTicket.LOTTO_PRICE;

public abstract class LottoMachine {

    public final List<LottoTicket> buyTickets(BettingMoneyDTO bettingMoney) {
        int ticketCount = bettingMoney.getBettingMoney() / LOTTO_PRICE;

        List<LottoTicket> lottoTickets = new ArrayList<>();
        for (int i = 0; i < ticketCount; i++) {
            lottoTickets.add(createOneTicket());
        }

        return lottoTickets;
    }

    public abstract LottoTicket createOneTicket();

    public final WinningLotto createWinningLotto(WinningLottoDTO winningLottoDTO) {
        Set<LottoBall> winningLotto = new HashSet<>();
        for (int number : winningLottoDTO.getWinningNumbers()) {
            winningLotto.add(LottoBallFactory.getLottoBallByNumber(number));
        }
        return new WinningLotto(winningLotto, LottoBallFactory.getLottoBallByNumber(winningLottoDTO.getBonusNumber()));
    }
}
