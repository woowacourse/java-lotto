package lotto.domain.ticket;

import lotto.domain.ticket.ball.LottoBall;
import lotto.domain.ticket.ball.LottoFactory;
import lotto.view.dto.BettingMoneyRequestDTO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static lotto.domain.ticket.LottoTicket.LOTTO_PRICE;

public class RealLottoStore extends LottoStore {
    private static final int BALL_COUNT = 6;
    private static final List<LottoBall> balls = LottoFactory.getInstance();

    @Override
    public List<LottoTicket> buyTicket(BettingMoneyRequestDTO bettingMoney) {
        int ticketCount = bettingMoney.getBettingMoney() / LOTTO_PRICE;

        List<LottoTicket> lottoTickets = new ArrayList<>();
        for (int i = 0; i < ticketCount; i++) {
            lottoTickets.add(getTicket());
        }

        return lottoTickets;
    }

    private static LottoTicket getTicket() {
        Collections.shuffle(balls);
        Set<LottoBall> lottoBalls = new HashSet<>();
        for (int i = 0; i < BALL_COUNT; i++) {
            lottoBalls.add(balls.get((i)));
        }
        return new LottoTicket(lottoBalls);
    }
}
