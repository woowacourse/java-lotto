package domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoRandomGeneratorTest {
  @DisplayName("생성된_로또_숫자는_6개입니다")
  @Test
  void 생성된_로또_숫자는_6개입니다() {
    assertThat(LottoRandomGenerator.generateNumbers().size()).isEqualTo(6);
  }
}