package lotto.model.winning_lotto.vo;

import static lotto.model.winning_lotto.vo.BonusNumber.MAX;
import static lotto.model.winning_lotto.vo.BonusNumber.MIN;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BonusNumberTest {

    @ParameterizedTest
    @ValueSource(ints = {MIN - 1, MAX + 1})
    void 보너스_번호_생성_시_최대_최소_범위를_넘어가면_예외_발생(int value) {

        assertThatIllegalArgumentException().isThrownBy(() -> BonusNumber.from(value));
    }
}