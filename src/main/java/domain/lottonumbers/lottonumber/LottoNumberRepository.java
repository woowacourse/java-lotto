package domain.lottonumbers.lottonumber;

import java.util.Map;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toMap;

public class LottoNumberRepository {
    static final Map<Integer, LottoNumber> cache;

    static {
        cache = IntStream.rangeClosed(LottoNumber.MIN_BOUND, LottoNumber.MAX_BOUND)
                .boxed()
                .collect(toMap(i -> i, LottoNumber::new));
    }
}
