package src.model.winning_lotto.vo;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static src.model.winning_lotto.vo.BonusNumber.MAX_NUMBER_RANGE;
import static src.model.winning_lotto.vo.BonusNumber.MIN_NUMBER_RANGE;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BonusNumberTest {

    @ParameterizedTest
    @ValueSource(ints = {MIN_NUMBER_RANGE - 1, MAX_NUMBER_RANGE + 1})
    void 보너스_번호_생성_시_최대_최소_범위를_넘어가면_예외_발생(int value) {

        assertThatIllegalArgumentException().isThrownBy(() -> BonusNumber.from(value));
    }
}