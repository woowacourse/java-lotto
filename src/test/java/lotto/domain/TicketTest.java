package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import lotto.domain.utils.BasicLottoNumberGenerator;

public class TicketTest {

    @Test
    @DisplayName("티켓생성시 LottoNumber의 숫자와 개수가 정상이면 예외를 던지지 말아야 합니다.")
    void ticketCreateValidTest() {
        assertThatCode(() -> new Ticket(Set.of(new LottoNumber(1),
            new LottoNumber(2),
            new LottoNumber(3),
            new LottoNumber(4),
            new LottoNumber(5),
            new LottoNumber(6)), false))
            .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("티켓생성시 LottoNumber의 개수가 5개면 예외를 던져야 합니다.")
    void ticketCreateInvalidTest() {
        assertThatThrownBy(() -> new Ticket(Set.of(new LottoNumber(1),
            new LottoNumber(2),
            new LottoNumber(3),
            new LottoNumber(4),
            new LottoNumber(5)), false))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("티켓 끼리의 비교는 LottoNumber의 숫자가 같으면 같은 티켓으로 판단합니다.")
    void ticketCompareTest() {
        Ticket ticket = Ticket.createByAuto(new BasicLottoNumberGenerator());
        Set<LottoNumber> lottoNumbers = Set.of(new LottoNumber(1),
            new LottoNumber(2),
            new LottoNumber(3),
            new LottoNumber(4),
            new LottoNumber(5),
            new LottoNumber(6));
        assertThat(ticket.getLottoNumbers()).isEqualTo(lottoNumbers);
    }

    @ParameterizedTest(name = "{index}: {1}")
    @MethodSource("parameters")
    @DisplayName("로또 번호 입력 실패 테스트")
    void invalidTest(List<Integer> input, String testName) {
        assertThatThrownBy(() -> Ticket.createByManual(input))
            .isInstanceOf(IllegalArgumentException.class);
    }

    private static Stream<Arguments> parameters() {
        return Stream.of(
            Arguments.of(Arrays.asList(1, 2, 3, 4, 5), "5개입력"),
            Arguments.of(Arrays.asList(1, 2, 3, 4, 5, 6, 7), "7개입력"),
            Arguments.of(Arrays.asList(1, 2, 3, 4, 5, 5), "중복 번호 입력"));
    }
}
