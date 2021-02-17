package lotto.util;

import static org.assertj.core.api.Assertions.assertThat;

import lotto.domain.Lotto;
import lotto.domain.LottoNumber;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoGeneratorTest {

  @Test
  @DisplayName("수동 로또 번호 생성")
  void createInputLotto() {
    Lotto lotto = LottoGenerator.generate(() -> LottoNumber.asList(1,2,3,4,5,6));
    int expectedMatchCount = 6;
    int matchCount = lotto.matchCount(new Lotto(LottoNumber.asList(1, 2, 3, 4, 5, 6)));
    assertThat(matchCount).isEqualTo(expectedMatchCount);
  }
}