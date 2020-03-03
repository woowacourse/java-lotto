package lotto.domain;

import lotto.exception.UnderLottoUnitMoney;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MoneyTest {

    @Test
    @DisplayName("양수인지 테스트")
    void validate_positive_number() {
        String input = "1000";

        assertThatCode(() -> new Money(input)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("음수인지 테스트")
    void throw_validate_positive_number() {
        String input = "-1";

        assertThatThrownBy(() -> new Money(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"1.1", "가", "", " "})
    @DisplayName("정수만 가능한지 예외 테스트")
    void throw_validate_integer_number_format(String input) {
        assertThatThrownBy(() -> new Money(input))
                .isInstanceOf(NumberFormatException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"1000", "2000", "3000", "40000"})
    @DisplayName("정수만 가능한지 테스트")
    void validate_integer_number_format(String input) {
        assertThatCode(() -> new Money(input))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("로또 한장 단위만큼 입력됐는지 테스트")
    void validate_lotto_unit() {
        String input = "999";

        assertThatThrownBy(() -> new Money(input)).isInstanceOf(UnderLottoUnitMoney.class);
    }

    @Test
    @DisplayName("로또 한장 단위만큼 입력됐는지 테스트")
    void throw_validate_lotto_unit() {
        String input = "1001";

        assertThatCode(() -> new Money(input)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("거스름돈을 return 하는 테스트")
    void changeMoney() {
        Money money = new Money("1100");

        Assertions.assertThat(money.changeMoney()).isEqualTo("100");
    }

    @Test
    @DisplayName("로또 티켓 겟수를 return 하는 테스트")
    void generateLottoTicketCount() {
        Money money = new Money("1100");

        Assertions.assertThat(money.generateLottoTicketCount()).isEqualTo("1");
    }
}