package lotto.domain.util;

import java.util.List;
import lotto.domain.LottoNumber;

@FunctionalInterface
public interface LottoGenerator {
    List<LottoNumber> generate(int size);
}
