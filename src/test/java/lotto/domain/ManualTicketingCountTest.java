package lotto.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class ManualTicketingCountTest {
    @Test
    @DisplayName("수동 로또 발급 개수를 정상적으로 받은 경우")
    void manual_ticketing_count() {
        String input = "3";

        Assertions.assertThatCode(() -> new ManualTicketingCount(input)).doesNotThrowAnyException();
    }

    @ParameterizedTest
    @ValueSource(strings = {"-1", "가", " ", ""})
    @DisplayName("수동 로또 발급 개수를 비정상적으로 받은 경우")
    void throw_manual_ticketing_count(String input) {
        Assertions.assertThatThrownBy(() -> new ManualTicketingCount(input));
    }
}
