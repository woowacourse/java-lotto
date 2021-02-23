package domain.lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class TicketCountTest {

    @DisplayName("ticketCount 정상 생성 테스트.")
    @Test
    void ticketCountGenerateTest() {
        assertThatCode(() -> new TicketCount(1))
                .doesNotThrowAnyException();
    }

    @DisplayName("ticketCount의 수가 0이하이면 에러가 발생한다")
    @ParameterizedTest
    @ValueSource(ints = {-1, 0})
    void ticketCountGenerateErrorTest(int value) {
        assertThatThrownBy(() -> new TicketCount(value))
                .isInstanceOf(IllegalArgumentException.class);
    }
}