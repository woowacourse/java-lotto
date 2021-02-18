package domain.lotto;

import static lotto.view.messages.ErrorMessages.LOTTO_NUMBER_RANGE_ERROR;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import lotto.domain.lotto.LottoNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoNumberTest {

    @Test
    @DisplayName("로또 번호는 1부터 45까지 숫자다.")
    public void check_car_name_exception() {
        assertThatThrownBy(() -> new LottoNumber(46))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining(LOTTO_NUMBER_RANGE_ERROR.getMessage());
    }

}
