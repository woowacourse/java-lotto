package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Set;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class TicketTest {

    @Test
    @DisplayName("티켓 정상 생성")
    void 티켓생성() {
        Ticket ticket = new Ticket(() -> Set.of(new LottoNumber(1),
            new LottoNumber(2),
            new LottoNumber(3),
            new LottoNumber(4),
            new LottoNumber(5),
            new LottoNumber(6)));
        assertThat(ticket.getLottoNumbers()).isEqualTo(Set.of(new LottoNumber(1),
            new LottoNumber(2),
            new LottoNumber(3),
            new LottoNumber(4),
            new LottoNumber(5),
            new LottoNumber(6))
        );
    }

    @Test
    @DisplayName("티켓 입력 정상일때")
    void 티켓번호개수_정상입력() {
        assertThatCode(() -> new Ticket(Set.of(new LottoNumber(1),
            new LottoNumber(2),
            new LottoNumber(3),
            new LottoNumber(4),
            new LottoNumber(5),
            new LottoNumber(6))))
            .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("티켓 번호 개수 5개일때 예외테스트")
    void 티켓번호개수_5개() {
        assertThatThrownBy(() -> new Ticket(Set.of(new LottoNumber(1),
            new LottoNumber(2),
            new LottoNumber(3),
            new LottoNumber(4),
            new LottoNumber(5))))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("문자열로 로또 정상 생성")
    void 입력받은_문자열로_로또번호_정상생성() {
        assertThatCode(() -> Ticket.of("1, 2, 3, 4, 5, 6"))
            .doesNotThrowAnyException();
    }

    @ParameterizedTest(name = "{index}: {1}")
    @MethodSource("parameters")
    @DisplayName("로또 번호 입력 테스트")
    void 로또번호입력(String input, String testName) {
        assertThatThrownBy(() -> Ticket.of(input))
            .isInstanceOf(IllegalArgumentException.class);
    }

    private static Stream<Arguments> parameters() {
        return Stream.of(
            Arguments.of("1, 2, 3, 4, 5", "5개입력"),
            Arguments.of("1, 2, 3, 4, 5, 6, 7", "7개입력"),
            Arguments.of("1, 2, 3, 4, 5, a", "문자 개입력"),
            Arguments.of("1, 2, 3, 4, 5, 5", "중복 번호 입력"),
            Arguments.of("1, 2, 3, 4, 5, 6, ", "마지막에 구분자 추가"),
            Arguments.of(null, "널 입력"));
    }
}
