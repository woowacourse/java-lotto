package lotto.domain;

import lotto.view.OutputView;
import org.junit.jupiter.api.Test;

class LottoResultTest {

  @Test
  void test() {
    LottoResult lottoResult = new LottoResult();
    lottoResult.add(LottoRank.FIFTH);
    lottoResult.add(LottoRank.FIFTH);
    lottoResult.add(LottoRank.FIFTH);
    lottoResult.add(LottoRank.FIFTH);
    lottoResult.add(LottoRank.FIFTH);

    System.out.println(lottoResult.winningProfit());
    OutputView.printLottoResult(lottoResult);
  }
}