package lotto.domain.ticket;

import lotto.domain.ticket.ball.LottoBall;
import lotto.domain.ticket.ball.LottoBallFactory;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class LottoMachineForTest extends LottoMachine {

    @Override
    public LottoTicket createOneTicket() {
        return new LottoTicket(getTestBalls());
    }

    private Set<LottoBall> getTestBalls() {
        int[] fixedNumber = new int[]{1, 2, 3, 4, 5, 6};
        return Arrays.stream(fixedNumber)
                .mapToObj(LottoBallFactory::getLottoBallByNumber)
                .collect(Collectors.toSet());
    }
}
