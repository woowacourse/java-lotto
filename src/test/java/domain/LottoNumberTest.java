package domain;

import static org.assertj.core.api.Assertions.*;

import exception.LottoException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import view.validator.LottoInputValidator;

public class LottoNumberTest {

  @ParameterizedTest
  @ValueSource(ints = {0,1,2,46})
  @DisplayName("로또_번호는_1부터_45_값만_가질_수_있습니다")
  void 로또_번호는_1부터_45_값만_가질_수_있습니다(int number) {
    assertThatThrownBy(() -> {
      new LottoNumber(number);
    }).isInstanceOf(LottoException.class);
  }

  @ParameterizedTest
  @DisplayName("로또_번호는_숫자만_가능합니다")
  @ValueSource(strings = {"one","하나"," "})
  void 로또_번호는_숫자만_가능합니다(String number) {

    assertThatThrownBy(() -> {
      LottoInputValidator.validateNumber(number);
    }).isInstanceOf(LottoException.class);
  }

}
