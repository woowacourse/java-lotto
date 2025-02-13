package domain;

import exception.LottoException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class WinningNumberTest {

    @DisplayName("당첨_번호가_중복되는_경우_예외_발생")
    @ParameterizedTest
    @ValueSource(strings = {"1,2,3,4,5,5"})
    void 당첨_번호가_중복되는_경우_예외_발생(String numbers) {
        Assertions.assertThatThrownBy(() -> {
            new WinningNumber(numbers);
        }).isInstanceOf(LottoException.class);
    }

    @DisplayName("당첨_번호는_6개여야_합니다")
    @ParameterizedTest
    @ValueSource(strings = {"1,2,3,4,5,6,7"})
    void 당첨_번호는_6개여야_합니다(String numbers) {
        Assertions.assertThatThrownBy(() -> {
            new WinningNumber(numbers);
        }).isInstanceOf(LottoException.class);
    }

    @DisplayName("당첨_번호는_1_45_사이여야_합니다")
    @ParameterizedTest
    @ValueSource(strings = {"0,2,3,4,5,44", "1,2,3,4,5,46"})
    void 당첨_번호는_1_45_사이여야_합니다(String numbers) {
        Assertions.assertThatThrownBy(() -> {
            new WinningNumber(numbers);
        }).isInstanceOf(LottoException.class);
    }

    @DisplayName("당첨_번호는_숫자가_아니면_예외를_발생한다")
    @ParameterizedTest
    @ValueSource(strings = {"a,2,3,4,5,5"})
    void 당첨_번호는_숫자가_아니면_예외를_발생한다(String numbers) {
        Assertions.assertThatThrownBy(() -> {
            new WinningNumber(numbers);
        }).isInstanceOf(LottoException.class);
    }

}
