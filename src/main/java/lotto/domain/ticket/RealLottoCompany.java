package lotto.domain.ticket;

import lotto.domain.ticket.ball.LottoBall;
import lotto.domain.ticket.ball.LottoFactory;
import lotto.view.dto.BettingMoneyRequestDTO;

import java.util.*;

import static lotto.domain.ticket.LottoTicket.BALL_COUNT;
import static lotto.domain.ticket.LottoTicket.LOTTO_PRICE;

public class RealLottoCompany extends LottoCompany {
    private static final List<LottoBall> balls = LottoFactory.getInstance();
    private static final int ZERO = 0;

    private static LottoTicket getTicket() {
        Collections.shuffle(balls);
        Set<LottoBall> lottoBalls = new HashSet<>();
        for (int i = ZERO; i < BALL_COUNT; i++) {
            lottoBalls.add(balls.get((i)));
        }
        return new LottoTicket(lottoBalls);
    }

    @Override
    public List<LottoTicket> buyTicket(BettingMoneyRequestDTO bettingMoney) {
        int ticketCount = bettingMoney.getBettingMoney() / LOTTO_PRICE;

        List<LottoTicket> lottoTickets = new ArrayList<>();
        for (int i = ZERO; i < ticketCount; i++) {
            lottoTickets.add(getTicket());
        }

        return lottoTickets;
    }
}
