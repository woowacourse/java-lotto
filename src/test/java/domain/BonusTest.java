package domain;

import exception.LottoException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class BonusTest {

    @DisplayName("보너스_번호가_1_45내의_숫자가_아니면_예외가_발생한다")
    @ParameterizedTest
    @ValueSource(strings = {"0", "46"})
    void 보너스_번호가_1_45내의_숫자가_아니면_예외가_발생한다(String numbers) {
        Assertions.assertThatThrownBy(() -> {
            new BonusNumber(numbers);
        }).isInstanceOf(LottoException.class);
    }

    @DisplayName("보너스_번호가_숫자가_아니면_예외가_발생한다")
    @ParameterizedTest
    @ValueSource(strings = {"a", ""})
    void 보너스_번호가_숫자가_아니면_예외가_발생한다(String numbers) {
        Assertions.assertThatThrownBy(() -> {
            new BonusNumber(numbers);
        }).isInstanceOf(LottoException.class);
    }

}
