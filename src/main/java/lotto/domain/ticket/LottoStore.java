package lotto.domain.ticket;

import lotto.domain.result.win.WinningLotto;
import lotto.domain.ticket.ball.LottoBall;
import lotto.domain.ticket.ball.LottoBallFactory;
import lotto.view.dto.WinningLottoRequestDTO;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class LottoStore {
    public static WinningLotto makeWinningLotto(WinningLottoRequestDTO winningLottoRequestDTO) {
        Set<LottoBall> winningLotto = new HashSet<>();
        for (int number : winningLottoRequestDTO.getWinningNumbers()) {
            winningLotto.add(LottoBallFactory.findLottoBallByNumber(number));
        }
        return new WinningLotto(winningLotto, LottoBallFactory.findLottoBallByNumber(winningLottoRequestDTO.getBonusNumber()));
    }

    protected abstract LottoTicket getTicket();

    public List<LottoTicket> buyTicket(BettingMoney bettingMoney) {
        int ticketCount = bettingMoney.getTicketCount();

        List<LottoTicket> lottoTickets = new ArrayList<>();
        for (int i = 0; i < ticketCount; i++) {
            lottoTickets.add(getTicket());
        }

        return lottoTickets;
    }
}
