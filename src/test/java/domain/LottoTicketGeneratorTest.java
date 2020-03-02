package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class LottoTicketGeneratorTest {
    @DisplayName("Should_로또 티켓 유효성 통과_When_String 을 받아 로또 티켓 하나를 생성")
    @ParameterizedTest
    @ValueSource(strings = {"", "fdsf",})
    void createLottoTicketTest(String input) {
        Assertions.assertThatThrownBy(() -> {
            LottoTicketGenerator.createLottoTicket(input);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
