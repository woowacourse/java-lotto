package lotto.util;

import lotto.domain.LottoRank;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoResultTest {

  private LottoResult lottoResult;

  @BeforeEach
  void setUp() {
    lottoResult = new LottoResult();
  }

  @Test
  @DisplayName("로또 랭크 추가 테스트")
  void addTest() {

    int expectedCount = 2;
    lottoResult.add(LottoRank.FIFTH);
    lottoResult.add(LottoRank.FIFTH);

    int count = lottoResult.rankMatch().get(LottoRank.FIFTH);

    Assertions.assertThat(count).isEqualTo(expectedCount);
  }

  @Test
  @DisplayName("로또 수익률 테스트")
  void profitTest() {

    double expectedProfit = 500.0;

    lottoResult.add(LottoRank.FIFTH);
    lottoResult.add(LottoRank.FIFTH);
    lottoResult.add(LottoRank.FIFTH);
    lottoResult.add(LottoRank.FIFTH);
    lottoResult.add(LottoRank.FIFTH);

    double profit = lottoResult.winningProfit();

    Assertions.assertThat(profit).isEqualTo(expectedProfit);
  }
}