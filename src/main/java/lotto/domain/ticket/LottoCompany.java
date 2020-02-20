package lotto.domain.ticket;

import lotto.domain.result.win.WinningLotto;
import lotto.domain.ticket.ball.LottoBall;
import lotto.domain.ticket.ball.LottoFactory;
import lotto.view.dto.BettingMoneyRequestDTO;
import lotto.view.dto.WinningLottoRequestDTO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static lotto.domain.ticket.LottoTicket.LOTTO_PRICE;

public class LottoCompany {
    private static final int BALL_COUNT = 6;
    private static final List<LottoBall> balls = LottoFactory.getInstance();

    public static List<LottoTicket> buyTicket(BettingMoneyRequestDTO bettingMoney) {
        int ticketCount = bettingMoney.getBettingMoney() / LOTTO_PRICE;

        List<LottoTicket> lottoTickets = new ArrayList<>();
        for (int i = 0; i < ticketCount; i++) {
            lottoTickets.add(getTicket());
        }

        return lottoTickets;
    }

    public static WinningLotto makeWinningLotto(WinningLottoRequestDTO winningLottoRequestDTO) {
        Set<LottoBall> winningLotto = new HashSet<>();
        for (int number : winningLottoRequestDTO.getWinningNumbers()) {
            winningLotto.add(LottoFactory.findLottoBallByNumber(number));
        }
        return new WinningLotto(winningLotto, LottoFactory.findLottoBallByNumber(winningLottoRequestDTO.getBonusNumber()));
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
