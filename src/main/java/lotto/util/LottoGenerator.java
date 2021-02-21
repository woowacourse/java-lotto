package lotto.util;

import lotto.domain.Lotto;

public class LottoGenerator {

  private LottoGenerator() {
  }

  public static Lotto generate(LottoNumberStrategy lottoNumberStrategy) {
    return new Lotto(lottoNumberStrategy.generate());
  }
}
