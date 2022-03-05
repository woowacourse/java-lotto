package lotto.domain.lottonumbergenerator;

import java.util.List;
import lotto.domain.LottoNumber;

public interface LottoNumberGenerator {

    List<List<LottoNumber>> getLottoNumbersBy(int count);
}
