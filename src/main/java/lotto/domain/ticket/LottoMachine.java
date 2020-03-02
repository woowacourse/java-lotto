package lotto.domain.ticket;

import lotto.domain.customer.Customer;
import lotto.domain.ticket.ball.LottoBall;
import lotto.domain.ticket.ball.LottoBallFactory;

import java.util.List;

public interface LottoMachine {
    List<LottoBall> balls = LottoBallFactory.getInstance();

    List<LottoTicket> buyTickets(Customer customer);
}
