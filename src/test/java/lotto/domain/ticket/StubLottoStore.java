package lotto.domain.ticket;

import lotto.domain.ticket.ball.LottoBall;
import lotto.domain.ticket.ball.LottoFactory;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class StubLottoStore extends LottoStore {

    @Override
    protected LottoTicket getTicket() {
        return new LottoTicket(getStubBalls());
    }

    private Set<LottoBall> getStubBalls() {
        LottoBall one = LottoFactory.findLottoBallByNumber(1);
        LottoBall two = LottoFactory.findLottoBallByNumber(2);
        LottoBall three = LottoFactory.findLottoBallByNumber(3);
        LottoBall four = LottoFactory.findLottoBallByNumber(4);
        LottoBall five = LottoFactory.findLottoBallByNumber(5);
        LottoBall six = LottoFactory.findLottoBallByNumber(6);
        return new HashSet<>(Arrays.asList(one, two, three, four, five, six));
    }
}
