package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import lotto.domain.Lotto;
import org.junit.jupiter.api.Test;

class LottoTest {

  @Test
  void createLotto() {
    Lotto lotto = new Lotto(Arrays.asList(1,2,3,4,5,6));

    assertThat(lotto)
        .isEqualTo(new Lotto(Arrays.asList(1,2,3,4,5,6)));
  }


}