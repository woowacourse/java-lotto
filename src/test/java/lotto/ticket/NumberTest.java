package lotto.ticket;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static lotto.ticket.TicketValidation.ERROR_MESSAGE_INVALID_INPUT;
import static lotto.ticket.TicketValidation.ERROR_MESSAGE_INVALID_RANGE;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class NumberTest {
    @Test
    @DisplayName("번호 생성 테스트")
    void create() {
        Number number = Number.valueOf("43");
        assertThat(number).isEqualTo(Number.valueOf("43"));
    }

    @Test
    @DisplayName("생성된 숫자 갯수 확인")
    void numberCacheCount() {
        assertThat(Number.values().size()).isEqualTo(45);
    }

    @Test
    @DisplayName("검증: 숫자가 아닌 값이 들어온 경우")
    void checkNotNumber() {
        assertThatThrownBy(() ->
                Number.valueOf("*")
        ).isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ERROR_MESSAGE_INVALID_INPUT);
    }

    @Test
    @DisplayName("검증: 1~45 를 벗어나는 수를 가져오려는 경우")
    void checkNotInRangeNumber() {
        assertThatThrownBy(() -> {
            Number.valueOf("0");
            Number.valueOf("46");
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ERROR_MESSAGE_INVALID_RANGE);
    }
}
