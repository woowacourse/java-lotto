package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Set;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@SuppressWarnings("NonAsciiCharacters")
public class TicketTest {

    @Test
    void 티켓_전략_객체_생성자_확인() {
        Ticket ticket = new Ticket(() -> Set.of(LottoNumber.valueOf(1),
                LottoNumber.valueOf(2),
                LottoNumber.valueOf(3),
                LottoNumber.valueOf(4),
                LottoNumber.valueOf(5),
                LottoNumber.valueOf(6)));
        assertThat(ticket.getLottoNumbers()).isEqualTo(Set.of(LottoNumber.valueOf(1),
                LottoNumber.valueOf(2),
                LottoNumber.valueOf(3),
                LottoNumber.valueOf(4),
                LottoNumber.valueOf(5),
                LottoNumber.valueOf(6))
        );
    }

    @Test
    void 티켓_번호_개수_정상() {
        assertThatCode(() -> new Ticket(Set.of(LottoNumber.valueOf(1),
                LottoNumber.valueOf(2),
                LottoNumber.valueOf(3),
                LottoNumber.valueOf(4),
                LottoNumber.valueOf(5),
                LottoNumber.valueOf(6))))
                .doesNotThrowAnyException();
    }

    @Test
    void 번호_개수_5개_티켓_생성_실패() {
        assertThatThrownBy(() -> new Ticket(Set.of(LottoNumber.valueOf(1),
                LottoNumber.valueOf(2),
                LottoNumber.valueOf(3),
                LottoNumber.valueOf(4),
                LottoNumber.valueOf(5))))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 입력받은_문자열로_로또번호_정상생성() {
        assertThatCode(() -> Ticket.from("1, 2, 3, 4, 5, 6"))
                .doesNotThrowAnyException();
    }

    @ParameterizedTest(name = "{index}: {1}")
    @MethodSource("parameters")
    @DisplayName("로또 번호 입력 테스트")
    void 로또번호입력(String input, String testName) {
        assertThatThrownBy(() -> Ticket.from(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    static Stream<Arguments> parameters() {
        return Stream.of(
                Arguments.of("1, 2, 3, 4, 5", "5개입력"),
                Arguments.of("1, 2, 3, 4, 5, 6, 7", "7개입력"),
                Arguments.of("1, 2, 3, 4, 5, a", "문자 개입력"),
                Arguments.of("1, 2, 3, 4, 5, 5", "중복 번호 입력"),
                Arguments.of("1, 2, 3, 4, 5, 6, ", "마지막에 구분자 추가"),
                Arguments.of(null, "널 입력"));
    }
}
