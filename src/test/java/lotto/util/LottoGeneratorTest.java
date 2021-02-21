package lotto.util;

import static org.assertj.core.api.Assertions.assertThat;

import lotto.domain.Lotto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

class LottoGeneratorTest {

  @Test
  @DisplayName("랜덤 로또 번호 생성")
  void createRandomLotto() {
    Lotto lotto = LottoGenerator.generate();
    assertThat(lotto).isNotNull();
  }

  @Test
  @DisplayName("수동 로또 번호 생성")
  void createInputLotto() {
    Lotto lotto = LottoGenerator.generate(Arrays.asList(1, 2, 3, 4, 5, 45));
    assertThat(lotto).isNotNull();
  }
}