package lotto.domain.lottoticket;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lotto.domain.LottoNumber;

final class LottoNumbersCache {
    private static final String NOT_INSTANTIATION_ERROR = "LottoNumbersCache 객체를 생성할 수 없습니다.";
    private static final int MIN_NUMBER = LottoNumber.MIN;
    private static final int MAX_NUMBER = LottoNumber.MAX;

    static List<LottoNumber> cache = new ArrayList<>();

    static {
        for (int i = MIN_NUMBER; i <= MAX_NUMBER; i++) {
            cache.add(new LottoNumber(i));
        }
        cache = Collections.unmodifiableList(cache);
    }

    private LottoNumbersCache() throws InstantiationException {
        throw new InstantiationException(NOT_INSTANTIATION_ERROR);
    }
}
