package validator;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
class WinLottoNumbersValidatorTest {
    @Test
    void 로또_번호_중복_확인() {
        assertThatThrownBy(() -> WinLottoNumbersValidator.validate(Arrays.asList(1, 2, 3, 4, 5, 2)))
                .isInstanceOf(Exception.class);
    }

    @Test
    void 로또_번호와_보너스_중복_확인() {
        assertThatThrownBy(() -> WinLottoNumbersValidator.validateBonus(Arrays.asList(1, 2, 3, 4, 5, 6), 5))
                .isInstanceOf(Exception.class);
    }
}