package lotto.util;

import static org.assertj.core.api.Assertions.assertThat;

import lotto.domain.Lotto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LottoGeneratorTest {

  private LottoGenerator lottoGenerator;

  @BeforeEach
  void setUp() {
    lottoGenerator = new LottoGenerator();
  }

  @Test
  void createRandomLotto() {
    Lotto lotto = lottoGenerator.generate();
    assertThat(lotto).isNotNull();
    System.out.println(lotto.toString());
  }

  @Test
  void createInputLotto() {
    Lotto lotto = lottoGenerator.generate(1, 2, 3, 4, 5, 45);
    assertThat(lotto).isNotNull();
    System.out.println(lotto.toString());
  }
}