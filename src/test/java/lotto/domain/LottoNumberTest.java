package lotto.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import lotto.exception.LottoNumberException;
import org.junit.jupiter.api.Test;

class LottoNumberTest {

  @Test
  void createSuccess() {
    // 45 안 일때
    LottoNumber lottoNumber = LottoNumber.of(30);
    List<LottoNumber> lottoNumbers = LottoNumber.asList(1, 2, 3, 4, 5, 6);
  }

  @Test
  void createFail() {
    // 음수 또는 45 가 넘어갈 떄
    assertThatThrownBy(() -> LottoNumber.of(60)).isInstanceOf(LottoNumberException.class);
    assertThatThrownBy(() -> LottoNumber.of(-1)).isInstanceOf(LottoNumberException.class);
    assertThatThrownBy(() -> LottoNumber.asList(-1, 1, 2, 3, 4, 5))
        .isInstanceOf(LottoNumberException.class);
  }


}