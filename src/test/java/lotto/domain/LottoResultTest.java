package lotto.domain;

import org.junit.jupiter.api.Test;

class LottoResultTest {

  @Test
  void test() {
    LottoResult lottoResult = new LottoResult();
    lottoResult.add(LottoRank.FIFTH);
    lottoResult.add(LottoRank.NONE);
    lottoResult.add(LottoRank.NONE);
    System.out.println(lottoResult.winningProfit());
  }
}