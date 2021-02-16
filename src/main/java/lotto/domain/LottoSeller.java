package lotto.domain;

import java.util.ArrayList;
import java.util.List;
import lotto.util.LottoGenerator;

public class LottoSeller {
  private static final int LOTTO_PRICE = 1000;
  private final LottoGenerator lottoGenerator;

  public LottoSeller(LottoGenerator lottoGenerator) {
    this.lottoGenerator = lottoGenerator;
  }

  public LottoGroup sellLotto(int price) {
    int count = price / LOTTO_PRICE;
    if(count < 1) {
      throw new IllegalArgumentException();
    }

    LottoGroup lottoGroup = new LottoGroup();
    for (int i = 0; i < count; i++) {
      lottoGroup.addLotto(lottoGenerator.generate());
    }
    return lottoGroup;
  }
}
