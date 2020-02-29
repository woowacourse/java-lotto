package lotto.domain.ticket;

import lotto.domain.customer.Customer;
import lotto.domain.ticket.ball.LottoBall;
import lotto.domain.ticket.ball.LottoBallFactory;

import java.util.List;

public interface LottoMachine {
    List<LottoBall> balls = LottoBallFactory.getInstance();
    String MESSAGE_FOR_UNMATCHED_SIZE = "입력받은 수동 로또 번호 수가 구매하려는 수와 일치하지 않습니다";

    List<LottoTicket> buyTickets(Customer customer);
}
