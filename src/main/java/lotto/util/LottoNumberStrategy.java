package lotto.util;

import java.util.List;
import lotto.domain.LottoNumber;

@FunctionalInterface
public interface LottoNumberStrategy {

  List<LottoNumber> generate();
}
