package lotto.domain.ticket;

import lotto.domain.customer.Customer;
import lotto.domain.ticket.ball.LottoBall;
import lotto.util.NullOrEmptyValidator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static lotto.domain.ticket.LottoTicket.LOTTO_BALL_COUNT;

public class AutoLottoMachine implements LottoMachine {

    @Override
    public List<LottoTicket> buyTickets(Customer customer) {
        NullOrEmptyValidator.isNull(customer);

        List<LottoTicket> lottoTickets = new ArrayList<>();
        for (int i = 0; i < customer.getPurchaseInfo().getNumberOfLeftTickets(); i++) {
            lottoTickets.add(createOneTicket());
        }

        return lottoTickets;
    }

    private LottoTicket createOneTicket() {
        Collections.shuffle(balls);

        Set<LottoBall> lottoBalls = balls.stream()
                .limit(LOTTO_BALL_COUNT)
                .collect(Collectors.toSet());

        return new LottoTicket(lottoBalls);
    }
}
