package lotto.domain.lottoticket;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lotto.domain.LottoNumber;

class LottoNumbersCache {
    private static final String NOT_INSTANTIATION_ERROR = "LottoNumbersCache 객체를 생성할 수 없습니다.";

    static final List<LottoNumber> cache;

    static {
        List<LottoNumber> lottoNumbers = new ArrayList<>();
        for (int i = LottoNumber.MIN; i <= LottoNumber.MAX; i++) {
            lottoNumbers.add(new LottoNumber(i));
        }
        cache = Collections.unmodifiableList(lottoNumbers);
    }

    private LottoNumbersCache() throws InstantiationException {
        throw new InstantiationException(NOT_INSTANTIATION_ERROR);
    }
}
