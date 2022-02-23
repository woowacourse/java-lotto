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
        Amount amount = new Amount();

        // when
        int count = amount.calculate(14000);

        // then
        assertThat(count).isEqualTo(14);
    }
}
