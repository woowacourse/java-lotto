package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
public class MoneyTest {

    @DisplayName("구입 금액을 기반으로 티켓 갯수를 반환한다.")
    @Test
    void 로또_티켓_정상_발급() {
        // given
        Money money = new Money(14000);

        // when
        int count = money.calculate();

        // then
        assertThat(count).isEqualTo(14);
    }

    @DisplayName("구입 금액이 1000원 미만인 경우 예외를 던진다.")
    @Test
    void 로또_티켓_금액_부족() {
        // given & when & then
        assertThatThrownBy(() -> new Money(500))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("거스름돈이 생긴 경우 가능한 티켓 갯수만 반환한다.")
    @Test
    void 가능한_금액_만큼_반환() {
        // given
        Money money = new Money(14500);

        // when
        int count = money.calculate();

        // then
        assertThat(count).isEqualTo(14);
    }

    @DisplayName("구입금액이 음수인 경우 예외를 던진다.")
    @Test
    void 음수인경우_테스트() {
        // given & when & then
        assertThatThrownBy(() -> new Money(-1))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
