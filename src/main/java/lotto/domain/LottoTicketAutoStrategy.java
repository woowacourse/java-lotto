package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoTicketAutoStrategy implements LottoTicketStrategy {

    private static final int LOTTO_NUMBER_SIZE = 6;

    private static final List<LottoNumber> lottoNumberCandidate = new ArrayList<>();

    static {
        for (int i = LottoNumber.MINIMUM_RANGE; i <= LottoNumber.MAXIMUM_RANGE; i++) {
            lottoNumberCandidate.add(LottoNumber.valueOf(i));
        }
    }

    @Override
    public LottoTicket generate() {
        List<LottoNumber> numbers = new ArrayList<>(lottoNumberCandidate);
        Collections.shuffle(numbers);
        numbers = numbers.subList(0, LOTTO_NUMBER_SIZE);
        Collections.sort(numbers);
        return new LottoTicket(numbers);
    }
}
