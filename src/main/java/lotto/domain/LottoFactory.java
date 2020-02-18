package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoFactory {
    private static final int TICKET_PRICE = 1000;
    private static final List<LottoBall> balls = makeBalls();
    private static final int BALL_COUNT = 6;

    private static List<LottoBall> makeBalls() {
        return IntStream.rangeClosed(1, 45)
                .mapToObj(LottoBall::new)
                .collect(Collectors.toList());
    }

    public static List<LottoTicket> buyTicket(int money) {
        int amount = money / TICKET_PRICE;

        List<LottoTicket> lottoTickets = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
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
