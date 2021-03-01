package lotto.util;

import lotto.domain.lottos.LottoNumber;
import lotto.domain.lottos.LottoTicket;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RandomNumberGenerator implements LottoNumberGenerator {

    private static final int MIN_NUMBER_RANGE = 1;
    private static final int MAX_NUMBER_RANGE = 45;

    private static final List<LottoNumber> CACHE;

    static {
        CACHE = IntStream.range(MIN_NUMBER_RANGE, MAX_NUMBER_RANGE)
                .boxed()
                .map(LottoNumber::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<LottoNumber> generateNumbers() {
        Collections.shuffle(CACHE);
        return CACHE
                .stream()
                .limit(LottoTicket.LOTTO_NUMBER_SIZE)
                .sorted()
                .collect(Collectors.collectingAndThen(Collectors.toList(), Collections::unmodifiableList));
    }
}
