package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@SuppressWarnings("NonAsciiCharacters")
public class AmountTest {

    @ParameterizedTest(name = "{index}: {1}")
    @MethodSource("invalidParameters")
    @DisplayName("투입금액 생성 유효성 검사")
    void invalidCreate(int amount, String testName) {
        /* when */
        assertThatThrownBy(() -> new Amount(amount))
                /* then */
                .isInstanceOf(IllegalArgumentException.class);
    }

    static Stream<Arguments> invalidParameters() {
        return Stream.of(
                Arguments.of(0, "투입금액 0"),
                Arguments.of(100, "천원단위로 안나누어떨어질때"),
                Arguments.of(-1, "투입금액 음수")
        );
    }

    @Test
    void 투입금액이_정상() {
        /* when */
        assertThatCode(() -> new Amount(10000))
                /* then */
                .doesNotThrowAnyException();
    }

    @Test
    void 티켓구매_개수_확인() {
        /* given */
        Amount amount = new Amount(14000);
        /* when */
        assertThat(amount.getTicketCount())
                /* then */
                .isEqualTo(14);
    }
}
