package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AutoLottoTicketFactory implements LottoTicketFactory {
    public static final int FROM_INDEX = 0;
    public static final int TO_INDEX = 6;
    private final static List<Integer> lottoNumbers = IntStream
        .rangeClosed(LottoNumber.MIN_RANGE, LottoNumber.MAX_RANGE).boxed()
        .collect(Collectors.toList());

    @Override
    public LottoTicket createLottoTicket() {
        return new LottoTicket(generateRandomNumbers());
    }

    private List<Integer> generateRandomNumbers() {
        Collections.shuffle(lottoNumbers);
        return new ArrayList<>(lottoNumbers.subList(FROM_INDEX, TO_INDEX));
    }
}
