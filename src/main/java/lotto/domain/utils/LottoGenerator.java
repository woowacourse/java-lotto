package lotto.domain.utils;

import lotto.domain.model.Lotto;
import lotto.domain.model.Number;

import java.util.List;

public interface LottoGenerator {
     List<Lotto> generate(List<Number> lottoNumber);
}
