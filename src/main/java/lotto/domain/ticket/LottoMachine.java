package lotto.domain.ticket;

import lotto.domain.ticket.ball.LottoBall;
import lotto.domain.ticket.ball.LottoBallFactory;
import lotto.view.dto.BettingMoneyDTO;
import lotto.view.dto.WinLottoTicketDTO;

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

    public final WinLottoTicket createWinLottoTicket(WinLottoTicketDTO winLottoTicketDTO) {
        Set<LottoBall> winBalls = new HashSet<>();
        for (int number : winLottoTicketDTO.getWinningNumbers()) {
            winBalls.add(LottoBallFactory.getLottoBallByNumber(number));
        }
        return new WinLottoTicket(new LottoTicket(winBalls), LottoBallFactory.getLottoBallByNumber(winLottoTicketDTO.getBonusNumber()));
    }
}
