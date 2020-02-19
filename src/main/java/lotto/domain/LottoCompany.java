package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    public static WinningLotto makeWinningLotto(int bonusNumber, int... numbers) {
        Set<LottoBall> winningLotto = new HashSet<>();
        for (int number : numbers) {
            winningLotto.add(LottoFactory.findLottoBallByNumber(number));
        }
        return new WinningLotto(winningLotto, LottoFactory.findLottoBallByNumber(bonusNumber));
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
