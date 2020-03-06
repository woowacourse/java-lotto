package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.Arrays;

public class LottoTicketGeneratorTest {
    @DisplayName("수동으로 입력한 잘못된 문자열에 대해 로또 티켓 생성시 예외 출력 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"", "1, 2, 3, 4, 5, f", "1, 2, 3, 4, 5     , ", "0, 2, 3, 4, 5, 46"})
    void generateManualLottoTicketWithInvalidInputTest(String input) {
        Assertions.assertThatThrownBy(() -> {
            LottoTicketGenerator.generateManualLottoTicket(input);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("수동으로 입력한 올바른 로또 번호에 대해 로또 티켓 생성 테스트")
    @Test
    void generateManualLottoTicketWithValidInputTest() {
        LottoTicket lottoTicket = LottoTicketGenerator.generateManualLottoTicket("1, 2, 3, 4, 5, 6");

        Assertions.assertThat(lottoTicket).isEqualTo(new LottoTicket(
                new ArrayList(Arrays.asList(
                        new LottoNumber(1),
                        new LottoNumber(2),
                        new LottoNumber(3),
                        new LottoNumber(4),
                        new LottoNumber(5),
                        new LottoNumber(6)
                ))
        ));
    }

    @DisplayName("자동으로 생성된 두 LottoTicket 이 다른 티켓인지 테스트")
    @Test
    void equalsTest() {
        LottoTicket input = LottoTicketGenerator.generateAutoLottoTicket();

        LottoTicket expected = LottoTicketGenerator.generateAutoLottoTicket();
        Assertions.assertThat(input).isNotEqualTo(expected);
    }
}
