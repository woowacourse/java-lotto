package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class LottoTicketGeneratorTest {
    @DisplayName("String으로 입력받았을 때 로또티켓 생성해주는 메서드 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"", "fdsf",})
    void createLottoTicketTest(String input) {
        Assertions.assertThatThrownBy(() -> {
            LottoTicketGenerator.createLottoTicket(input);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
