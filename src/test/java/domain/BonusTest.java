package domain;

import exception.LottoException;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import view.validator.LottoInputValidator;

public class BonusTest {

  @DisplayName("보너스_번호가_1_45내의_숫자가_아니면_예외가_발생한다")
  @ParameterizedTest
  @ValueSource(ints = {0, 46})
  void 보너스_번호가_1_45내의_숫자가_아니면_예외가_발생한다(int number) {
    Assertions.assertThatThrownBy(() -> {
      new BonusNumber(number);
    }).isInstanceOf(LottoException.class);
  }

  @DisplayName("보너스_번호가_숫자가_아니면_예외가_발생한다")
  @ParameterizedTest
  @ValueSource(strings = {"a", ""})
  void 보너스_번호가_숫자가_아니면_예외가_발생한다(String number) {
    Assertions.assertThatThrownBy(() -> {
      LottoInputValidator.validateNumber(number);
    }).isInstanceOf(LottoException.class);
  }

  @DisplayName("보너스_번호가_당첨_번호에_존재하는_경우_예외가_발생한다")
  @Test
  void 보너스_번호가_당첨_번호에_존재하는_경우_예외가_발생한다() {
    Assertions.assertThatThrownBy(() -> {
      WinningNumber winningNumber = new WinningNumber(List.of(1,2,3,4,5,6));
      new WinningLotto(winningNumber, new BonusNumber(6));
    }).isInstanceOf(LottoException.class);
  }

}
