package domain;

import domain.ball.LottoBall;
import domain.ball.LottoBallFactory;
import domain.ball.LottoBalls;
import domain.lotto.LottoTicket;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static domain.ball.LottoBalls.LOTTO_BALL_SIZE;

public class RandomLottoMachine {
    public List<LottoTicket> makeTickets(int ticketCount) {
        return IntStream.range(0, ticketCount)
                .mapToObj(count -> makeTicket())
                .collect(Collectors.toList());
    }

    private LottoTicket makeTicket() {
        List<LottoBall> lottoBalls = getRandomLottoBalls();
        return new LottoTicket(new LottoBalls(lottoBalls));
    }

    private List<LottoBall> getRandomLottoBalls() {
        List<LottoBall> lottoBalls = LottoBallFactory.getLottoBalls();
        Collections.shuffle(lottoBalls);
        return lottoBalls.stream()
                .limit(LOTTO_BALL_SIZE)
                .sorted()
                .collect(Collectors.toList());
    }
}
