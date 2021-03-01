package domain;

import domain.ball.LottoBall;
import domain.ball.LottoBalls;
import domain.lotto.LottoTicket;
import domain.lotto.LottoTickets;
import domain.lotto.TicketCount;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static domain.ball.LottoBalls.LOTTO_BALL_SIZE;

public class LottoMachine {
    public LottoTickets makeLottoTickets(final List<List<Integer>> manualTicketsNumbers, final TicketCount randomTicketCount) {
        List<LottoTicket> manualLottoTickets = makeRandomTickets(randomTicketCount.getTicketCount());
        List<LottoTicket> randomLottoTickets = makeManualTickets(manualTicketsNumbers);
        List<LottoTicket> lottoTickets = Stream.concat(manualLottoTickets.stream(), randomLottoTickets.stream()).collect(Collectors.toList());
        return new LottoTickets(lottoTickets);
    }

    public List<LottoTicket> makeRandomTickets(final int ticketCount) {
        return IntStream.range(0, ticketCount)
                .mapToObj(count -> makeRandomTicket())
                .collect(Collectors.toList());
    }

    private LottoTicket makeRandomTicket() {
        List<LottoBall> lottoBalls = getRandomLottoBalls();
        return new LottoTicket(new LottoBalls(lottoBalls));
    }

    public List<LottoTicket> makeManualTickets(final List<List<Integer>> manualTicketsNumbers) {
        return manualTicketsNumbers.stream()
                .map(this::makeManualTicket)
                .collect(Collectors.toList());
    }

    private LottoTicket makeManualTicket(final List<Integer> manualTicketNumbers) {
        List<LottoBall> lottoBalls = manualTicketNumbers.stream()
                .map(LottoBall::from)
                .collect(Collectors.toList());
        return new LottoTicket(new LottoBalls(lottoBalls));
    }

    private List<LottoBall> getRandomLottoBalls() {
        List<LottoBall> lottoBalls = LottoBall.getLottoBalls();
        Collections.shuffle(lottoBalls);
        return lottoBalls.stream()
                .limit(LOTTO_BALL_SIZE)
                .sorted()
                .collect(Collectors.toList());
    }
}
