package domain.lotto;

import static lotto.view.messages.ErrorMessages.LOTTO_NUMBER_RANGE_ERROR;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import lotto.domain.lotto.LottoNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoNumberTest {

    @Test
    @DisplayName("1부터 45사이의 숫자가 아닐경 로또 번호 생성시 예외가 발생한다.")
    public void testLottoNumberRange() {
        assertThatThrownBy(() -> new LottoNumber(46))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining(LOTTO_NUMBER_RANGE_ERROR.getMessage());
    }

}