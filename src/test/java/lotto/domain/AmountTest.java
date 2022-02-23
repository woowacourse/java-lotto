package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
public class AmountTest {

    @DisplayName("구입 금액을 기반으로 티켓 갯수를 반환한다.")
    @Test
    void 로또_티켓_정상_발급() {
        // given
        Amount amount = new Amount(14000);

        // when
        int count = amount.calculate();

        // then
        assertThat(count).isEqualTo(14);
    }

    @DisplayName("구입 금액이 1000원 미만인 경우 예외를 던진다.")
    @Test
    void 로또_티켓_금액_부족() {
        // given & when & then
        assertThatThrownBy(() -> new Amount(500))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
