package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class WinningLottoTicketTest {
    @DisplayName("WinningLottoTicket 생성자에 잘못된 값을 넣은 경우 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"", "1, 2, 3, 4, 5, f", "1, 2, 3, 4, 5     , ", "0, 2, 3, 4, 5, 46"})
    void WinningLottoTicketConstructorWithInvalidInput(String input) {
        Assertions.assertThatThrownBy(() -> {
            new WinningLottoTicket(input);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("BonusBall 초기화 테스트")
    @Test
    void initializeBonusBallTest() {
        WinningLottoTicket winningLottoTicket = new WinningLottoTicket("1, 2, 3, 4, 5, 6");
        Assertions.assertThatThrownBy(() -> {
            winningLottoTicket.initializeBonusBall(new LottoNumber(5));
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
