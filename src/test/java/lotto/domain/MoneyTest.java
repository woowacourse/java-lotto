package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class MoneyTest {

    @ParameterizedTest
    @ValueSource(strings = {"1000", "10000", "95000"})
    void 양수이면서_1000원_단위이면_통과(String input) {
        // given
        final Money money = new Money(input);

        // when
        // then
        assertThat(money.getAmount()).isEqualTo(Integer.parseInt(input));
    }

    @Test
    void 로또_구입금액이_음수이면_예외처리() {
        // given
        // when
        // then
        assertThatThrownBy(() -> new Money("-100"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"123", "1001", "99999"})
    void 로또_구입금액이_1000원_단위가_아니라면_예외처리(String input) {
        // given
        // when
        // then
        assertThatThrownBy(() -> new Money(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"123", "-1000"})
        // given
        // when
        // then
    void 양수이지만_1000원_단위가_아니거나_1000원_단위이지만_양수가_아니면_예외(String input) {
        assertThatThrownBy(() -> new Money(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 로또티켓_개수가_1000원_단위로_나누었을_때_몫과_같으면_통과() {
        // given
        String purchaseAmount = "5000";
        int result = 5;

        // when
        final Money money = new Money(purchaseAmount);

        // then
        assertThat(money.getLottoTicketCount()).isEqualTo(result);
    }

}
