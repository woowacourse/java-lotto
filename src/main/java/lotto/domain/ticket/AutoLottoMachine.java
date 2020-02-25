package lotto.domain.ticket;

import lotto.domain.ticket.ball.LottoBall;
import lotto.domain.ticket.ball.LottoBallFactory;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static lotto.domain.ticket.LottoTicket.LOTTO_BALL_COUNT;

public class AutoLottoMachine extends LottoMachine {
    private static final List<LottoBall> balls = LottoBallFactory.getInstance();

    public LottoTicket createOneTicket() {
        Collections.shuffle(balls);

        Set<LottoBall> lottoBalls = balls.stream()
                .limit(LOTTO_BALL_COUNT)
                .collect(Collectors.toSet());

        return new LottoTicket(lottoBalls);
    }
}
