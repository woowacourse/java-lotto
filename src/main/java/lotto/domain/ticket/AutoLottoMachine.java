package lotto.domain.ticket;

import lotto.domain.ticket.ball.LottoBall;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static lotto.domain.ticket.LottoTicket.LOTTO_BALL_COUNT;

public class AutoLottoMachine extends LottoMachine {
    @Override
    public List<LottoTicket> buyTickets(int numberOfTickets) {
        List<LottoTicket> lottoTickets = new ArrayList<>();
        for (int i = 0; i < numberOfTickets; i++) {
            lottoTickets.add(createOneTicket());
        }

        return lottoTickets;
    }

    @Override
    public LottoTicket createOneTicket() {
        Collections.shuffle(balls);

        Set<LottoBall> lottoBalls = balls.stream()
                .limit(LOTTO_BALL_COUNT)
                .collect(Collectors.toSet());

        return new LottoTicket(lottoBalls);
    }
}
