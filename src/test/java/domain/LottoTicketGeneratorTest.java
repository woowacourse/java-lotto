package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class LottoTicketGeneratorTest {
    @DisplayName("로또 티켓 생성 유효성 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"", "fdsf",})
    void createLottoTicketTest(String input) {
        Assertions.assertThatThrownBy(() -> {
            LottoTicketGenerator.createLottoTicket(input);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
