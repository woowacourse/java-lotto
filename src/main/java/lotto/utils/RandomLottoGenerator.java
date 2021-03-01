package lotto.utils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lotto.domain.lotto.LottoNumber;
import lotto.domain.lotto.LottoTicket;

public class RandomLottoGenerator implements LottoGenerator {

    private static final List<LottoNumber> SOURCE_LOTTO_NUMBERS;

    static {
        SOURCE_LOTTO_NUMBERS = IntStream
                .rangeClosed(LottoNumber.MINIMUM_VALUE, LottoNumber.MAXIMUM_VALUE)
                .boxed().map(LottoNumber::new)
                .collect(Collectors.toList());
    }

    @Override
    public LottoTicket generateLottoTicket() {
        return new LottoTicket(getShuffledLottoNumbers());
    }

    private List<LottoNumber> getShuffledLottoNumbers() {
        Collections.shuffle(SOURCE_LOTTO_NUMBERS);
        return SOURCE_LOTTO_NUMBERS.subList(0, LottoTicket.SIZE_OF_LOTTO_NUMBERS);
    }
}
