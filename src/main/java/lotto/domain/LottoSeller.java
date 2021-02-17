package lotto.domain;

import lotto.util.LottoGenerator;

public class LottoSeller {

  private static final int LOTTO_PRICE = 1000;

  public LottoGroup sellLotto(int price) {
    int count = price / LOTTO_PRICE;
    if (count < 1) {
      throw new IllegalArgumentException();
    }

    LottoGroup lottoGroup = new LottoGroup();
    for (int i = 0; i < count; i++) {
      lottoGroup.addLotto(LottoGenerator.generate());
    }
    return lottoGroup;
  }

  public static int lottoPrice() {
    return LOTTO_PRICE;
  }
}
