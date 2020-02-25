package lotto.domain.ticket.strategy;

import lotto.domain.ticket.LottoTicket;
import lotto.domain.ticket.manual.ManualNumber;
import lotto.domain.ticket.number.LottoNumber;
import lotto.domain.ticket.number.LottoNumberFactory;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RandomLottoMachine implements LottoMachine {
    private static final List<LottoNumber> LOTTO_NUMBERS = LottoNumberFactory.getInstance();

    @Override
    public List<LottoTicket> buyTickets(int ticketCount, List<ManualNumber> numbers) {
        return IntStream.range(0, ticketCount)
                .mapToObj(count -> this.makeLottoTicket())
                .collect(Collectors.toList());
    }

    private LottoTicket makeLottoTicket() {
        Collections.shuffle(LOTTO_NUMBERS);

        return LOTTO_NUMBERS.stream()
                .limit(LottoTicket.LOTTO_TICKET_SIZE)
                .collect(Collectors.collectingAndThen(Collectors.toSet(), LottoTicket::new));
    }

}
