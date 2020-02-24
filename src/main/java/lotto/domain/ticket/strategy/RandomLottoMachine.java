package lotto.domain.ticket.strategy;

import lotto.domain.ticket.LottoTicket;
import lotto.domain.ticket.ball.LottoNumber;
import lotto.domain.ticket.ball.LottoNumberFactory;
import lotto.domain.ticket.manual.ManualNumber;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RandomLottoMachine implements LottoMachine {
    private static final int START_LOTTO_BALL = 1;
    private static final int END_LOTTO_BALL = 6;
    private static final List<LottoNumber> LOTTO_NUMBERS = LottoNumberFactory.getInstance();

    @Override
    public List<LottoTicket> buyTickets(int ticketCount, List<ManualNumber> numbers) {
        return IntStream.range(0, ticketCount)
                .mapToObj(count -> this.makeTicket())
                .collect(Collectors.toList());
    }

    private LottoTicket makeTicket() {
        Collections.shuffle(LOTTO_NUMBERS);

        return IntStream.rangeClosed(START_LOTTO_BALL, END_LOTTO_BALL)
                .mapToObj(LOTTO_NUMBERS::get)
                .collect(Collectors.collectingAndThen(Collectors.toSet(), LottoTicket::new));
    }

}
