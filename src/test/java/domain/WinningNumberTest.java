package domain;

import exception.LottoException;
import java.util.List;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import view.validator.LottoInputValidator;

public class WinningNumberTest {

  @DisplayName("당첨_번호가_중복되는_경우_예외_발생")
  @Test
  void 당첨_번호가_중복되는_경우_예외_발생() {
    Assertions.assertThatThrownBy(() -> {
      new WinningNumber(List.of(1, 2, 3, 4, 5, 5));
    }).isInstanceOf(LottoException.class);
  }

  @DisplayName("당첨_번호는_6개여야_합니다")
  @Test
  void 당첨_번호는_6개여야_합니다() {
    Assertions.assertThatThrownBy(() -> {
      new WinningNumber(List.of(1, 2, 3, 4, 5, 6, 7));
    }).isInstanceOf(LottoException.class);
  }

  @DisplayName("당첨_번호는_1_45_사이여야_합니다")
  @ParameterizedTest
  @MethodSource("rangeTestData")
  void 당첨_번호는_1_45_사이여야_합니다(List<Integer> lottoNumbers) {
    System.out.println(lottoNumbers);
    Assertions.assertThatThrownBy(() -> {
      new WinningNumber(lottoNumbers);
    }).isInstanceOf(LottoException.class);
  }

  @DisplayName("당첨_번호가_숫자가_아닌_경우_예외를_발생합니다")
  @ParameterizedTest
  @ValueSource(strings = {"1,2,3,4,5,육", "일,이,삼,사,오,육"})
  void 당첨_번호가_숫자가_아닌_경우_예외를_발생합니다(String inputWinningNumber) {
    String[] numbers = inputWinningNumber.split(",", -1);
    Assertions.assertThatThrownBy(() -> {
      for (String number : numbers) {
        LottoInputValidator.validateNumber(number);
      }
    }).isInstanceOf(LottoException.class);
  }

  static Stream<Arguments> rangeTestData() {
    return Stream.of(
        Arguments.arguments(
            List.of(0, 2, 3, 4, 5, 44),
            List.of(1, 2, 3, 4, 5, 46)
        )
    );
  }

}
