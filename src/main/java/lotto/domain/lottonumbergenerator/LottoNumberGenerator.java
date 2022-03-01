package lotto.domain.lottonumbergenerator;

import java.util.List;
import lotto.domain.LottoNumber;

public interface LottoNumberGenerator {

    List<LottoNumber> getLottoNumbers(int count);
}
