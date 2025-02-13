package src.model.winning_lotto.vo;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static src.model.winning_lotto.vo.WinningLottoNumber.MAX_NUMBER_RANGE;
import static src.model.winning_lotto.vo.WinningLottoNumber.MIN_NUMBER_RANGE;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class WinningLottoNumberTest {

    @ParameterizedTest
    @ValueSource(ints = {MIN_NUMBER_RANGE - 1, MAX_NUMBER_RANGE + 1})
    void 당첨_번호_생성_시_최대_최소_범위를_넘어가면_예외_발생(int value) {

        assertThatIllegalArgumentException().isThrownBy(() -> WinningLottoNumber.from(value));
    }
}