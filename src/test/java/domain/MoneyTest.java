package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@SuppressWarnings("NonAsciiCharacters")
class MoneyTest {

    @Test
    void 돈_로또개수로_변경_확인() {
        Money money = new Money(20000);
        assertThat(money.toLottoCount())
            .isEqualTo(20);
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 200, 999})
    void 천_미만_값일_경우_예외처리(int input) {
        assertThatThrownBy(() -> new Money(input)).isInstanceOf(Exception.class);
    }

    @ParameterizedTest
    @ValueSource(ints = {1500, 2060, 9999})
    void 천으로_나누어_떨어지지_않는_경우_예외처리(int input) {
        assertThatThrownBy(() -> new Money(input)).isInstanceOf(Exception.class);
    }
}