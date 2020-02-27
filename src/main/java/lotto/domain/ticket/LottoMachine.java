package lotto.domain.ticket;

import lotto.domain.ticket.ball.LottoBall;
import lotto.domain.ticket.ball.LottoBallFactory;
import lotto.util.NullOrEmptyValidator;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class LottoMachine {
    static final List<LottoBall> balls = LottoBallFactory.getInstance();
    static final String MESSAGE_FOR_UNMATCHED_SIZE = "입력받은 수동 로또 번호 수가 구매하려는 수와 일치하지 않습니다";

    public abstract List<LottoTicket> buyTickets(int numberOfTickets);

    public abstract LottoTicket createOneTicket();

    public final WinLottoTicket createWinLottoTicket(List<Integer> winNumbers, int bonusNumber) {
        NullOrEmptyValidator.isNullOrEmpty(winNumbers);

        Set<LottoBall> winBalls = winNumbers.stream()
                .map(LottoBallFactory::getLottoBallByNumber)
                .collect(Collectors.toSet());

        LottoBall bonusBall = LottoBallFactory.getLottoBallByNumber(bonusNumber);

        return new WinLottoTicket(new LottoTicket(winBalls), bonusBall);
    }
}
