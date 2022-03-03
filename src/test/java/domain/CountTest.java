package domain;

import static org.assertj.core.api.AssertionsForClassTypes.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class CountTest {

    @DisplayName("로또 발급 횟수 생성을 확인한다.")
    @Test
    void count_create() {
        assertDoesNotThrow(() -> new Count(4));
    }

    @DisplayName("수동으로 발급할 로또개수가 정상적으로 입력되는지 확인한다.")
    @ParameterizedTest
    @CsvSource(value = {"0,0", "3,3"}, delimiter = ',')
    void manual_count_create(String input, int count) {
        Money money = new Money("3000");
        Count manualCount = Count.getManualCount(input, money);
        assertThat(manualCount).isEqualTo(new Count(count));
    }

    @DisplayName("수동으로 발급할 로또개수가 Null 또는 빈값일 경우 예외를 발생시킨다.")
    @ParameterizedTest
    @NullAndEmptySource
    void manual_count_null_or_empty(final String input) {
        Money money = new Money("3000");
        assertThatThrownBy(() -> Count.getManualCount(input, money))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("null 또는 빈값을 입력할 수 없습니다.");
    }

    @DisplayName("수동으로 발급할 로또개수가 양수 또는 0이 아닌 경우 예외를 발생시킨다.")
    @ParameterizedTest
    @ValueSource(strings = {"a", "-1", "e1"})
    void manual_count_invalid_format(final String input) {
        Money money = new Money("3000");
        assertThatThrownBy(() -> Count.getManualCount(input, money))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("잘못된 값을 입력하였습니다.");
    }

    @DisplayName("수동으로 발급할 로또개수가 구매금액에 따라 살 수 있는 로또 개수를 초과할 경우 예외를 발생시킨다.")
    @Test
    void manual_count_over_price() {
        Money money = new Money("3000");
        assertThatThrownBy(() -> Count.getManualCount("4", money))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("수동으로 구매하려는 로또 수가 구입금액보다 많습니다.");
    }

    @DisplayName("남은 로또 발급 횟수가 정상적으로 감소되는지 확인한다.")
    @Test
    void count_decrease() {
        Count count = new Count(2);
        count = count.decrease();

        assertThat(count).isEqualTo(new Count(1));
    }
}
