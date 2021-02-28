package lotto.domain.lottomachine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lotto.domain.ticket.LottoTicket;
import lotto.domain.ticket.LottoTickets;
import lotto.domain.number.LottoNumberFactory;

public class AutoLottoMachine {
    private static final int FROM_INDEX = 0;
    private static final int TO_INDEX = 6;
    private static final List<Integer> lottoNumbers = IntStream
        .rangeClosed(LottoNumberFactory.MIN_RANGE, LottoNumberFactory.MAX_RANGE)
        .boxed()
        .collect(Collectors.toList());

    public LottoTickets createTickets(int ticketCount) {
        return IntStream.range(0, ticketCount)
            .mapToObj(i -> new LottoTicket(shuffleNumbers()))
            .collect(Collectors.collectingAndThen(Collectors.toList(), LottoTickets::new));
    }

    private List<Integer> shuffleNumbers() {
        Collections.shuffle(lottoNumbers);
        return new ArrayList<>(lottoNumbers.subList(FROM_INDEX, TO_INDEX));
    }
}
