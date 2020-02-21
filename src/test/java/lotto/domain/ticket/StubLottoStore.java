package lotto.domain.ticket;

import lotto.domain.ticket.ball.LottoBall;
import lotto.domain.ticket.ball.LottoBallFactory;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class StubLottoStore extends LottoStore {

    @Override
    protected LottoTicket getTicket() {
        return new LottoTicket(getStubBalls());
    }

    private Set<LottoBall> getStubBalls() {
        LottoBall one = LottoBallFactory.findLottoBallByNumber(1);
        LottoBall two = LottoBallFactory.findLottoBallByNumber(2);
        LottoBall three = LottoBallFactory.findLottoBallByNumber(3);
        LottoBall four = LottoBallFactory.findLottoBallByNumber(4);
        LottoBall five = LottoBallFactory.findLottoBallByNumber(5);
        LottoBall six = LottoBallFactory.findLottoBallByNumber(6);
        return new HashSet<>(Arrays.asList(one, two, three, four, five, six));
    }
}
