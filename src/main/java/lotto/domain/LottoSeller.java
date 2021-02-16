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

  public List<Lotto> sellLotto(int price) {
    int count = price / LOTTO_PRICE;
    if(count < 1) {
      throw new IllegalArgumentException();
    }

    List<Lotto> lottos = new ArrayList<>();
    for (int i = 0; i < count; i++) {
      lottos.add(lottoGenerator.generate());
    }
    return lottos;
  }
}
