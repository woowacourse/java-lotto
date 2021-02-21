package lotto.util;

import lotto.domain.LottoGroup;
import lotto.domain.Money;
import lotto.exception.LottoPriceException;

public class LottoSeller {

  private static final int LOTTO_PRICE = 1000;
  private static final int MIN_COUNT = 1;

  public static int lottoPrice() {
    return LOTTO_PRICE;
  }

  public LottoGroup sellLotto(final Money money, LottoNumberStrategy lottoNumberStrategy) {
    int count = money.divide(LOTTO_PRICE);
    if (count < MIN_COUNT) {
      throw new LottoPriceException("가격이 부족합니다.");
    }

    LottoGroup lottoGroup = new LottoGroup();
    for (int i = 0; i < count; i++) {
      lottoGroup.addLotto(LottoGenerator.generate(lottoNumberStrategy));
    }
    return lottoGroup;
  }
}
