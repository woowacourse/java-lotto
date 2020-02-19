package lotto.domain;

import java.util.*;

public class LottoCompany {
    private static final int TICKET_PRICE = 1000;
    private static final int BALL_COUNT = 6;
    private static final List<LottoBall> balls = LottoFactory.getInstance();

    public List<LottoTicket> buyTicket(int money) {
        int amount = money / TICKET_PRICE;

        List<LottoTicket> lottoTickets = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            lottoTickets.add(getTicket());
        }

        return lottoTickets;
    }

    private LottoTicket getTicket() {
        Collections.shuffle(balls);
        Set<LottoBall> lottoBalls = new HashSet<>();
        for (int i = 0; i < BALL_COUNT; i++) {
            lottoBalls.add(balls.get((i)));
        }
        return new LottoTicket(lottoBalls);
    }
}
