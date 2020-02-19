package lotto.domain;

import java.util.*;

public class LottoCompany {
    private static final int TICKET_PRICE = 1000;
    private static final int BALL_COUNT = 6;
    private static final List<LottoBall> balls = LottoFactory.getInstance();

    public static List<LottoTicket> buyTicket(int money) {
        int amount = money / TICKET_PRICE;

        List<LottoTicket> lottoTickets = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            lottoTickets.add(getTicket());
        }

        return lottoTickets;
    }

    public static LottoTicket makeWinningLotto(int... numbers) {
        Set<LottoBall> winningLotto = new HashSet<>();
        for (int number : numbers) {
            winningLotto.add(LottoFactory.findLottoBallByNumber(number));
        }
        return new LottoTicket(winningLotto);
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
