package lotto.domain.lotto;

import lotto.exception.InvalidLottoNumbersException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LottoNumberTest {
    @Test
    void 제대로_입력받은_경우_제대로_로또숫자를_반환하는지_확인() {
        assertThat(LottoNumber.valueOf(6).toString()).isEqualTo(Integer.toString(6));
    }

    @Test
    void 로또에_사용되는_숫자가_아닌_숫자를_입력받은_경우1_예외_반환() {
        assertThrows(InvalidLottoNumbersException.class, () -> LottoNumber.valueOf(0));
    }

    @Test
    void 로또에_사용되는_숫자가_아닌_숫자를_입력받은_경우2_예외_반환() {
        assertThrows(InvalidLottoNumbersException.class, () -> LottoNumber.valueOf(46));
    }
}
