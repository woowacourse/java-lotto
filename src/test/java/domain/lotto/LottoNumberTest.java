package domain.lotto;

import static lotto.view.messages.ErrorMessages.LOTTO_NUMBER_RANGE_ERROR;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import lotto.domain.lotto.LottoNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoNumberTest {

    @Test
    @DisplayName("1부터 45까지 범위의 숫자로 로또 번호를 생성한다.")
    void testCreateLottoNumber() {
        for (int i = 0; i < 45; i++) {
            assertThat(new LottoNumber(i + 1).getValue()).isEqualTo(i + 1);
        }
    }

    @Test
    @DisplayName("1부터 45까지 범위가 아닌 숫자로 로또 번호 생성시 예외가 발생한다.")
    void testCreateLottoNumberException() {
        assertThatThrownBy(() -> new LottoNumber(0))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining(LOTTO_NUMBER_RANGE_ERROR.getMessage());

        assertThatThrownBy(() -> new LottoNumber(46))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining(LOTTO_NUMBER_RANGE_ERROR.getMessage());
    }

}