package lotto.domain.ticket;

import lotto.domain.number.LottoNumberFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AutoLottoTicketFactory implements LottoTicketFactory {
    public static final int FROM_INDEX = 0;
    public static final int TO_INDEX = 6;

    private static final List<Integer> lottoNumbers = IntStream
            .rangeClosed(LottoNumberFactory.MIN_RANGE, LottoNumberFactory.MAX_RANGE)
            .boxed()
            .collect(Collectors.toList());

    @Override
    public LottoTicket createLottoTicket() {
        return new LottoTicket(shuffleNumbers());
    }

    private List<Integer> shuffleNumbers() {
        Collections.shuffle(lottoNumbers);
        return new ArrayList<>(lottoNumbers.subList(FROM_INDEX, TO_INDEX));
    }
}
