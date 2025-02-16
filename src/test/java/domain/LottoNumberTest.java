package domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import exception.LottoException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class LottoNumberTest {

    @ParameterizedTest
    @ValueSource(ints = {0, 46, 99})
    @DisplayName("로또_숫자가_1_45내의_숫자가_아니면_예외가_발생한다")
    public void 로또_숫자가_1_45내의_숫자가_아니면_예외가_발생한다(int buyMoney) {
        assertThatThrownBy(() -> {
            new LottoNumber(buyMoney);
        }).isInstanceOf(LottoException.class);
    }
}
