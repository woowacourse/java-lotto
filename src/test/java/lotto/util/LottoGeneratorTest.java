package lotto.util;

import static org.assertj.core.api.Assertions.assertThat;

import lotto.domain.Lotto;
import org.junit.jupiter.api.Test;

class LottoGeneratorTest {

  @Test
  void createRandomLotto() {
    Lotto lotto = LottoGenerator.generate();
    assertThat(lotto).isNotNull();
  }

  @Test
  void createInputLotto() {
    Lotto lotto = LottoGenerator.generate(1, 2, 3, 4, 5, 45);
    assertThat(lotto).isNotNull();
  }
}