package lotto.model.winning_lotto.vo;

import static lotto.model.winning_lotto.vo.WinningLottoNumber.MAX;
import static lotto.model.winning_lotto.vo.WinningLottoNumber.MIN;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class WinningLottoNumberTest {

    @ParameterizedTest
    @ValueSource(ints = {MIN - 1, MAX + 1})
    void 당첨_번호_생성_시_최대_최소_범위를_넘어가면_예외_발생(int value) {

        assertThatIllegalArgumentException().isThrownBy(() -> WinningLottoNumber.from(value));
    }
}