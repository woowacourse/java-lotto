package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import lotto.util.LottoGenerator;
import org.junit.jupiter.api.Test;

class LottoGroupTest {

  @Test
  void createLottoGroup() {
    LottoGroup lottoGroup = new LottoGroup();
    lottoGroup.addLotto(LottoGenerator.generate());
    lottoGroup.addLotto(LottoGenerator.generate());
    assertThat(lottoGroup.size()).isEqualTo(2);
  }
}