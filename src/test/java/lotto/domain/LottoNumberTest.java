package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import lotto.exception.LottoNumberException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoNumberTest {

  @Test
  @DisplayName("로또 번호 생성 성공")
  void createSuccess() {
    LottoNumber lottoNumber = LottoNumber.of(30);
    List<LottoNumber> lottoNumbers = LottoNumber.asList(1, 2, 3, 4, 5, 6);

    assertThat(lottoNumber).isNotNull();
    assertThat(lottoNumbers).isNotNull();
  }

  @Test
  @DisplayName("로또 번호 생성 실패 - 음수, 중복, 45초과")
  void createFail() {
    assertThatThrownBy(() -> LottoNumber.of(60)).isInstanceOf(LottoNumberException.class);
    assertThatThrownBy(() -> LottoNumber.of(-1)).isInstanceOf(LottoNumberException.class);
    assertThatThrownBy(() -> LottoNumber.asList(-1, 1, 2, 3, 4, 5))
        .isInstanceOf(LottoNumberException.class);
  }

}