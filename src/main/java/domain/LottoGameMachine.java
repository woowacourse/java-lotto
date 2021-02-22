package domain;

import domain.ball.LottoBall;
import domain.ball.LottoBalls;
import domain.bettingMoney.BettingMoney;
import domain.lotto.LottoTicket;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoGameMachine {
    public List<LottoTicket> buyTickets(BettingMoney bettingMoney) {
        int ticketCount = bettingMoney.getTicketCount();
        return IntStream.range(0, ticketCount)
                .mapToObj(count -> makeTicket())
                .collect(Collectors.toList());
    }

    private LottoTicket makeTicket() {
        List<LottoBall> lottoBalls = LottoBalls.getRandomLottoBalls();
        return new LottoTicket(new LottoBalls(lottoBalls));
    }
}
