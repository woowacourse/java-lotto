package lotto.domain.ticket;

import lotto.domain.ticket.ball.LottoBall;
import lotto.domain.ticket.ball.LottoFactory;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RealLottoStore extends LottoStore {
    private static final int BALL_COUNT = 6;
    private static final List<LottoBall> balls = LottoFactory.getInstance();

    @Override
    protected LottoTicket getTicket() {
        Collections.shuffle(balls);
        Set<LottoBall> lottoBalls = new HashSet<>();
        for (int i = 0; i < BALL_COUNT; i++) {
            lottoBalls.add(balls.get((i)));
        }
        return new LottoTicket(lottoBalls);
    }
}
