package lotto.util;

import lotto.domain.LottoGroup;
import lotto.domain.Money;

public class LottoSeller {

  private static final int LOTTO_PRICE = 1000;

  public static int lottoPrice() {
    return LOTTO_PRICE;
  }

  public static Money sellLotto(final Money money, LottoGroup lottoGroup, LottoNumberStrategy lottoNumberStrategy) {
    Money change = money.minus(LOTTO_PRICE);
    lottoGroup.addLotto(LottoGenerator.generate(lottoNumberStrategy));
    return change;
  }
}
